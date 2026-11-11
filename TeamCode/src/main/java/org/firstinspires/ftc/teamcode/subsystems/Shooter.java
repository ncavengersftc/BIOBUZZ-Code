package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class Shooter extends SubsystemBase {
    private final DcMotorEx outtakeMotorLeft;

    private final PIDFController pidfController;


    public Shooter(HardwareMap hMap) {
        outtakeMotorLeft = hMap.get(DcMotorEx.class, "Shooter Motor");


        outtakeMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        pidfController = new PIDFController(0.001,0.0,0.00,0.0001);
    }


    public void shoot(double targetVelocity) {
        double currentVelocity = outtakeMotorLeft.getVelocity();
        double power = pidfController.calculate(currentVelocity, targetVelocity);

        outtakeMotorLeft.setPower(power);

    }


    public void stop() {
        pidfController.reset();
        outtakeMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        outtakeMotorLeft.setPower(0);

    }
}
