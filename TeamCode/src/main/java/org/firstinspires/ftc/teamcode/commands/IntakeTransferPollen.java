package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.IntakeTransfer;
public class IntakeTransferPollen extends CommandBase {
    private final IntakeTransfer intakeTransfer;

    /**
     * Creates an object of IntakePollen.
     *
     * @param intakeTransfer Intake subsystem.
     */
    public IntakeTransferPollen(IntakeTransfer intakeTransfer) {

        this.intakeTransfer = intakeTransfer;

        addRequirements(intakeTransfer);
    }

    /**
     * The Intake intakes the pollen at a speed of 25 percent.
     */
    @Override
    public void execute() {
        intakeTransfer.intakeTransfer(0.25);
    }
}
