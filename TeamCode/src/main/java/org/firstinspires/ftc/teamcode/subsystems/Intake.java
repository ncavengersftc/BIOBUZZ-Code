package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Intake extends SubsystemBase {
    private final MotorEx intakeMotor;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = new MotorEx(hardwareMap, "Intake Motor", Motor.GoBILDA.RPM_312);
    }

    public void intake(double power) {
        intakeMotor.set(power);
    }

    public void stop() {
        intakeMotor.set(0);
        intakeMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
    }
}
