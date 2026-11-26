package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class IntakeTransfer extends SubsystemBase {
    private final MotorEx intakeTransferMotor;

    /**
     * Creates the object of Intake.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public IntakeTransfer(HardwareMap hardwareMap) {
        intakeTransferMotor = new MotorEx(hardwareMap, "Intake Motor", Motor.GoBILDA.RPM_312);
        intakeTransferMotor.setInverted(true);

    }

    /**
     * Sets the intake motor power to the value, can control its direction and speed of rotation.
     *
     * @param power The power in which the intake motor should run at.
     */
    public void intake(double power) {
        intakeTransferMotor.set(power);
    }

    /**
     * The Intake motor stops rotating, while going on a FLOAT setting.
     */

    public void stop() {
        intakeTransferMotor.set(0);
        intakeTransferMotor.setZeroPowerBehavior(MotorEx.ZeroPowerBehavior.FLOAT);
    }

    public void intakeTransfer(double power) {
    }
}

