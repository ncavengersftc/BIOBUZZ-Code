package org.firstinspires.ftc.teamcode.commands;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
public class StopIntake extends CommandBase {
    private final Intake intake;
    public StopIntake(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }
    @Override
    public void execute() {
        intake.stop();
    }
}