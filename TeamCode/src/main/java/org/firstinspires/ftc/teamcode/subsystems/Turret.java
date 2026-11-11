package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {

    private final MotorEx turretMotor;
    private static final double GEAR_RATIO = 1.0; // NEED TO CHANGE
    private static final double MAX_DEG = 540.0; // NEED TO CHANGE (540 degrees is like 1.5 turns)

    public Turret(HardwareMap hardwareMap) {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.setRunMode(Motor.RunMode.PositionControl);
        // ticks to degrees
        turretMotor.setDistancePerPulse(360.0 / (turretMotor.getCPR() * GEAR_RATIO));
        // motor initialization
        turretMotor.setPositionCoefficient(0.01);
        turretMotor.setPositionTolerance(2.0);
        turretMotor.resetEncoder();
    }

    public void trackTarget(double tx) {
        if (Math.abs(tx) > 0.5) {
            // If turret spins away from hive, change - to +
            setTargetAngle(turretMotor.getDistance() - tx);
        }
    }

    public void setTargetAngle(double targetDegrees) {
        double currentDeg = turretMotor.getDistance();
        double targetDeg = currentDeg + Math.IEEEremainder(targetDegrees - currentDeg, 360.0);

        // Subtracts 360 degrees so that if it is past limit, it rotates backwards to reach setpoint
        while (targetDeg > MAX_DEG) targetDeg -= 360.0;
        while (targetDeg < -MAX_DEG) targetDeg += 360.0;

        turretMotor.setTargetDistance(targetDeg);
    }

    public double getTurns() {
        return turretMotor.getCurrentPosition();
    }

    public void stop() {
        turretMotor.stopMotor();
    }

    @Override
    public void periodic() {
        super.periodic();
        // Turret max speed limit is 60%
        turretMotor.set(0.6);
    }


}