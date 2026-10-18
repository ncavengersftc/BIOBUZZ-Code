package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {
    private final MotorEx shooterMotor;


    public Turret(HardwareMap hMap) {
        shooterMotor = new MotorEx(hMap, "Shooter Motor", 28.0, 6000);

    }

    public void shoot(double power) {
        shooterMotor.set(power);
    }

    public void stop() {
        shooterMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
        shooterMotor.set(0);
    }
}
