package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class Shooter extends SubsystemBase {
    private final DcMotorEx shooterMotor;

    private final PIDFController pidfController;


    public Shooter(HardwareMap hMap) {
        shooterMotor = hMap.get(DcMotorEx.class, "Shooter Motor");
        shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        pidfController = new PIDFController(0.001,0.0,0.00,0.0001);
    }


    public void shoot(double targetVelocity) {
        double currentVelocity = shooterMotor.getVelocity();
        double power = pidfController.calculate(currentVelocity, targetVelocity);

        shooterMotor.setPower(power);

    }


    public void stop() {
        pidfController.reset();
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        shooterMotor.setPower(0);

    }
}
