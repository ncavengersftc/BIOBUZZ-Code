package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.IntakeTransfer;
public class StopIntakeTransfer extends CommandBase {
    private final IntakeTransfer intakeTransfer;

    /**
     * Creates an object of StopIntake.
     *
     * @param intakeTransfer Intake subsystem.
     */

    public StopIntakeTransfer(IntakeTransfer intakeTransfer) {

        this.intakeTransfer = intakeTransfer;

        addRequirements(intakeTransfer);
    }

    /**
     * The Intake stops rotating.
     */
    @Override
    public void execute() {
        intakeTransfer.stop();
    }
}
