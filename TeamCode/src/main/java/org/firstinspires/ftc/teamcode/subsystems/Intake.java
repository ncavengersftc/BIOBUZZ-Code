package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Intake extends SubsystemBase {
    private final MotorEx intakeMotor;

    /**
     * Creates the object of Intake.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Intake(HardwareMap hardwareMap) {
        intakeMotor = new MotorEx(hardwareMap, "Intake Motor", Motor.GoBILDA.RPM_312);
        intakeMotor.setInverted(true);

    }

    /**
     * Sets the intake motor power to the value, can control its direction and speed of rotation.
     *
     * @param power The power in which the intake motor should run at.
     */
    public void intake(double power) {
        intakeMotor.set(power);
    }

    /**
     * The Intake motor stops rotating, while going on a FLOAT setting.
     */

    public void stop() {
        intakeMotor.set(0);
        intakeMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Returns the current encoder position of the intake motor.
     *
     * @return Current encoder position in ticks.
     */
    public int getCurrentPosition() {
        return intakeMotor.getCurrentPosition();
    }
}
