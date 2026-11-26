package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Shooter extends SubsystemBase {
    private final MotorEx shooterMotor;

    private final PIDFController pidfController;

    /**
     * Creates the object Shooter.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Shooter(HardwareMap hardwareMap) {
        shooterMotor = new MotorEx(hardwareMap, "Shooter Motor", 112, 6000);


        shooterMotor.setInverted(true);


        pidfController = new PIDFController(0.001,0.0,0.00,0.0001);
    }

    /**
     * Calculates the power needed using PIDF control to make the shooter motor reach the requested rps.
     *
     * @param rotationsPerSecond The speed at which the shooter motor should run at.
     */
    public void shoot(double rotationsPerSecond) {
        double targetTicks = rotationsPerSecond * 112;

        double currentVelocity = shooterMotor.getVelocity();
        double power = pidfController.calculate(currentVelocity, targetTicks);

        shooterMotor.set(power);

    }

    /**
     * Resets the PIDF controller and stops the shooter motor.
     */

    public void stop() {
        pidfController.reset();
        shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);

        shooterMotor.set(0);

    }
}
