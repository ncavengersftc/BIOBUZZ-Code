package org.firstinspires.ftc.teamcode.commands;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
public class StopOuttake extends CommandBase {
    private final Shooter shooter;
    public StopOuttake(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }
    @Override
    public void execute() {
        shooter.stop();
    }
}
