package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class Turret extends SubsystemBase {
    private final MotorEx turretMotor;
    //this is the set point of the turret
    private double targetDegrees = 0;

    /**
     * Creates the object of Turret.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Turret(HardwareMap hardwareMap) {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_117);
        turretMotor.resetEncoder();
        turretMotor.setRunMode(Motor.RunMode.PositionControl);
        turretMotor.setPositionCoefficient(Constants.TURRET_KP);
        turretMotor.setPositionTolerance(Constants.TURRET_TOLERANCE_TICKS);
        turretMotor.setDistancePerPulse(Constants.TURRET_DEGREES_PER_TICK);
        turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
        turretMotor.setTargetDistance(Constants.TURRET_MIN_DEG);
    }

    /**
     * Sets the target angle in degrees, strictly clamped between TURRET_MIN_DEG and TURRET_MAX_DEG.
     *
     * @param degrees Target angle in degrees.
     */
    public void setTargetAngle(double degrees) {
        //theoretical target degrees is the degrees that you want it at to get to plus the degrees inputed.
        double theoreticalTargetDegrees = degrees;

        //if the turret reaches more than the max, it just sets the max degrees as that.
        if (theoreticalTargetDegrees > Constants.TURRET_MAX_DEG) {
            theoreticalTargetDegrees = Constants.TURRET_MAX_DEG;
        }
        //if the turret reaches less than the min, it just sets the min degrees as that.
        else if (theoreticalTargetDegrees < Constants.TURRET_MIN_DEG) {
            theoreticalTargetDegrees = Constants.TURRET_MIN_DEG;
        }

        targetDegrees = theoreticalTargetDegrees;
    }

    public double getCurrentDistance() {
        return turretMotor.getDistance();
    }

    public int getCurrentTicks() {
        return turretMotor.getCurrentPosition();
    }

    public boolean atTarget() {
        return turretMotor.atTargetPosition();
    }

    /**
     * Tracks target using Limelight tx offset in degrees, strictly respecting MIN and MAX limits.
     *
     * @param tx Horizontal offset in degrees.
     */
    //target degrees
    public void trackTarget(double tx) {
        if (!Double.isFinite(tx) || Math.abs(tx) < Constants.TURRET_VISION_TOLERANCE_DEG) {
            return;
        }
        setTargetAngle(getCurrentDistance() + tx);
    }

    public void stop() {
        turretMotor.stopMotor();
    }

    @Override
    public void periodic() {
        super.periodic();
        // Strictly clamp targetDegrees between TURRET_MIN_DEG and TURRET_MAX_DEG every loop frame
        targetDegrees = Math.max(Constants.TURRET_MIN_DEG, Math.min(Constants.TURRET_MAX_DEG, targetDegrees));

        turretMotor.setTargetDistance(targetDegrees);
        // actually gives power to the motors so it can get to set point
        turretMotor.set(Constants.TURRET_MAX_POWER);
    }
}
