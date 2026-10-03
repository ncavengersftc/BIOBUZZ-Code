package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Shooter extends SubsystemBase{
    private final DcMotor outtakeMotorLeft;
    private final DcMotor outtakeMotorRight;

    public Shooter(HardwareMap hMap) {
        outtakeMotorLeft = hMap.get(DcMotor.class, "Outtake Motor Left");
        outtakeMotorRight = hMap.get(DcMotor.class, "Outtake Motor Right");
        outtakeMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeMotorRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void shoot(double power) {
        outtakeMotorLeft.setPower(power);
        outtakeMotorRight.setPower(power);
    }

    public void stop() {
        outtakeMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeMotorLeft.setPower(0);
        outtakeMotorRight.setPower(0);
    }
}
