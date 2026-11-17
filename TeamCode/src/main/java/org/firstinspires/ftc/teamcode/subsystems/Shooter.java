package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class Shooter extends SubsystemBase {
    private final DcMotorEx outtakeMotorLeft;

    private final PIDFController pidfController;

    /**
     * Creates the object Shooter.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Shooter(HardwareMap hardwareMap) {
        outtakeMotorLeft = hardwareMap.get(DcMotorEx.class, "Shooter Motor");


        outtakeMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        pidfController = new PIDFController(0.001,0.0,0.00,0.0001);
    }

    /**
     * Calculates the power needed using PIDF control to make the shooter motor reach the requested rps.
     *
     * @param rotationsPerSecond The speed at which the shooter motor should run at.
     */
    public void shoot(double rotationsPerSecond) {
        double targetTicks = rotationsPerSecond * 112;

        double currentVelocity = outtakeMotorLeft.getVelocity();
        double power = pidfController.calculate(currentVelocity, targetTicks);

        outtakeMotorLeft.setPower(power);

    }

    /**
     * Resets the PIDF controller and stops the shooter motor.
     */

    public void stop() {
        pidfController.reset();
        outtakeMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        outtakeMotorLeft.setPower(0);

    }
}