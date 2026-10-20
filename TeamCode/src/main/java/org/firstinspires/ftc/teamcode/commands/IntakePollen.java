package org.firstinspires.ftc.teamcode.commands;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
public class IntakePollen extends CommandBase {
    private final Intake intake;
    public IntakePollen(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }
    @Override
    public void execute() {
        intake.intake(0.25);
    }
}
