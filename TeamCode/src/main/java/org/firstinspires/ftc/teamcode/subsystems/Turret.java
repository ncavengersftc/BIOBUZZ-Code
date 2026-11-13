package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import com.seattlesolvers.solverslib.util.MathUtils;

import org.firstinspires.ftc.teamcode.Constants;

public class Turret extends SubsystemBase {
    private final MotorEx turretMotor;
    private double targetAngle = 0.0;
    private boolean isEnabled = true;
    private boolean isUnwinding = false;


    // Access MotorEx's protected controller to limit its output.
    private static final class TurretMotor extends MotorEx {
        private TurretMotor(HardwareMap hardwareMap) {
            super(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
            positionController.setMaxOutput(1.0);

            // The library skips this clamp inside tolerance.
            // Keep TURRET_KP * TURRET_TOLERANCE_DEG <= 1.
        }
    }


    // Constructor
    public Turret(HardwareMap hardwareMap) {
        turretMotor = new TurretMotor(hardwareMap);

        // Initialize at the centered, cable-neutral position.
        turretMotor.resetEncoder();
        turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        // Feedback and targets now use turret degrees.
        turretMotor.setDistancePerPulse(
                360.0 / (turretMotor.getCPR() * Constants.TURRET_GEAR_RATIO));
        turretMotor.setRunMode(Motor.RunMode.PositionControl);
        turretMotor.setPositionCoefficient(Constants.TURRET_KP);
        turretMotor.setPositionTolerance(Constants.TURRET_TOLERANCE_DEG);
        turretMotor.setTargetDistance(targetAngle);
    }


    // Call repeatedly with turret-mounted Limelight tx in degrees.
    public void trackTarget(double tx) {
        if (!Double.isFinite(tx)) {
            stop();
            return;
        }
        if (Math.abs(tx) > Constants.TURRET_VISION_TOLERANCE_DEG) {
            // Flip the sign if tracking turns the wrong way.
            setTargetAngle(getAngle() - tx);
        }
    }


    // Set an absolute angle from center, unwinding if outside the limits.
    public void setTargetAngle(double degrees) {
        // If input isn't a valid number.
        if (!Double.isFinite(degrees)) {
            stop();
            return;
        }

        // Finish the active unwind before accepting another target.
        if (isUnwinding) {
            return;
        }

        // If degrees is more than the maximum configured.
        if (degrees > Constants.TURRET_MAX_DEG) {
            isUnwinding = true;

            // Sets limit to be reversed (so 361 -> 1)
            degrees = Constants.TURRET_MAX_DEG - MathUtils.normalizeDegrees(
                    Constants.TURRET_MAX_DEG - degrees, true);
        }

        // If degrees is less than the minimum configured.
        else if (degrees < Constants.TURRET_MIN_DEG) {
            isUnwinding = true;

            // Sets limit to be reversed (so -361 -> -1)
            degrees = Constants.TURRET_MIN_DEG + MathUtils.normalizeDegrees(
                    degrees - Constants.TURRET_MIN_DEG, true);
        }

        // Stop if no equivalent angle fits; stop() also clears the unwind flag.
        if (!Double.isFinite(degrees) || degrees < Constants.TURRET_MIN_DEG
                || degrees > Constants.TURRET_MAX_DEG) {
            stop();
            return;
        }

        targetAngle = degrees;
        turretMotor.setTargetDistance(targetAngle);
        isEnabled = true;
    }


    // Increases degrees manually (so use controller buttons).
    public void manualControl(double turnDegrees) {
        setTargetAngle((isEnabled ? targetAngle : getAngle()) + turnDegrees);
    }


    // Returns current motor position in degrees.
    public double getAngle() {
        return turretMotor.getDistance();
    }


    // Stops the turret.
    public void stop() {
        isEnabled = false;
        isUnwinding = false;

        // Resets the position controller and stops the motor.
        turretMotor.set(0);
        turretMotor.stopMotor();
    }


    // Called every loop by the Command Scheduler (20 ms)
    @Override
    public void periodic() {
        // If the robot isn't enabled (stopped), doesn't run the loop.
        if (!isEnabled) {
            return;
        }

        // Sets max power the motors can run at.
        turretMotor.set(Constants.TURRET_MAX_POWER);

        // If the turret is done unwinding.
        if (isUnwinding && turretMotor.atTargetPosition()) {
            isUnwinding = false;
        }
    }
}
