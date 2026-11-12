package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {

    private final MotorEx turretMotor;
    private static final double GEAR_RATIO = 2.7;
    private static final double MIN_DEG = -360.0;
    private static final double MAX_DEG = 360.0;

    private double targetAngle = 0.0;
    private boolean isEnabled = true;

    public Turret(HardwareMap hardwareMap) {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.resetEncoder();
        turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        
        // Convert ticks to degrees
        turretMotor.setDistancePerPulse(360.0 / (turretMotor.getCPR() * GEAR_RATIO));
        
        // Configure PositionControl PID
        turretMotor.setRunMode(Motor.RunMode.PositionControl);
        turretMotor.setPositionCoefficient(0.01);
        turretMotor.setPositionTolerance(2.0);
        turretMotor.setTargetDistance(0.0);
    }

    // Adjusts turret based on vision horizontal offset (tx)
    public void trackTarget(double tx) {
        // Change - to + if it spins the wrong way
        if (Math.abs(tx) > 2.0) {
            setTargetAngle(getAngle() - tx);
        }
    }

    // Clamps target angle within safe cable limits
    public void setTargetAngle(double desiredDegrees) {
        isEnabled = true;
        targetAngle = Range.clip(desiredDegrees, MIN_DEG, MAX_DEG);
    }

    // Use to control turret manually
    public void changeAngleBy(double deltaDegrees) {
        setTargetAngle(targetAngle + deltaDegrees);
    }

    public void setTargetTurns(double targetTurns) {
        setTargetAngle(targetTurns * 360.0);
    }

    public double getAngle() {
        return turretMotor.getDistance();
    }

    public double getTurns() {
        return getAngle() / 360.0;
    }

    public void stop() {
        isEnabled = false;
        turretMotor.stopMotor();
    }

    @Override
    public void periodic() {
        super.periodic();
        if (isEnabled) {
            turretMotor.setTargetDistance(targetAngle);
            // Updates internal P-controller and caps max speed at 60%
            turretMotor.set(0.6); 
        }
    }
}
