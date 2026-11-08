package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {
    private final MotorEx turretMotor;


    public Turret(HardwareMap hardwareMap) {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.setRunMode(Motor.RunMode.PositionControl);

        // set and get the position coefficient
        turretMotor.setPositionCoefficient(0.05);
        turretMotor.setPositionTolerance(15);
    }

    public void setTargetTurns(double turns) {
        int targetTicks = (int) Math.round(turns * 537.7);
        turretMotor.setTargetPosition(targetTicks);

        // turns until target position
        while (!turretMotor.atTargetPosition()) {
            turretMotor.set(0.75);
        }

        turretMotor.stopMotor();
    }

    // this code makes sure that the turret is always facing  the hive,
    // No tag -> scan. Tag found -> turn toward it. Tag centered -> stop
    public void trackTarget(double tx) {

        // If no valid tag is detected
        if (!Double.isFinite(tx)) {
            turretMotor.set(0.1);
        }

        // Stop when tag is centered
        if (Math.abs(tx) < 1.0) {
            stop();
            return;
        }
        double power = tx * 0.015;
        power = Math.max(-0.25, Math.min(0.25, power));
        turretMotor.set(power);

    }

    public double getTurns() {
        return turretMotor.getCurrentPosition() / 537.7;
    }

    public void stop() {
        turretMotor.stopMotor();
    }
}



