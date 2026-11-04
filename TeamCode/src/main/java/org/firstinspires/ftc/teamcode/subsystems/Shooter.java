package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class Shooter extends SubsystemBase{
    private final DcMotorEx outtakeMotorLeft;
    private final DcMotorEx outtakeMotorRight;
    private PIDFController pidfController;

    public Shooter(HardwareMap hMap) {
        outtakeMotorLeft = hMap.get(DcMotorEx.class, "Outtake Motor Left");
        outtakeMotorRight = hMap.get(DcMotorEx.class, "Outtake Motor Right");
        outtakeMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        outtakeMotorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        pidfController = new PIDFController(0.001,0.0,0.00,0.0001);
    }

    public void shoot(double targetVelocity) {
        double currentVelocity = outtakeMotorLeft.getVelocity();
        double power = pidfController.calculate(currentVelocity, targetVelocity);
        outtakeMotorLeft.setPower(power);
        outtakeMotorRight.setPower(power);
    }

    public void stop() {
        pidfController.reset();
        outtakeMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        outtakeMotorLeft.setPower(0);
        outtakeMotorRight.setPower(0);
    }
}
