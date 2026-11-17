package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
public class IntakePollen extends CommandBase {
    private final Intake intake;

    /**
     * Creates an object of IntakePollen.
     *
     * @param intake Intake subsystem.
     */
    public IntakePollen(Intake intake) {

        this.intake = intake;

        addRequirements(intake);
    }

    /**
     * The Intake intakes the pollen at a speed of 25 percent.
     */
    @Override
    public void execute() {
        intake.intake(0.25);
    }
}
