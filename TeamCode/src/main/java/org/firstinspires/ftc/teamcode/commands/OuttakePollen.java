package org.firstinspires.ftc.teamcode.commands;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
public class OuttakePollen extends CommandBase {
    private final Shooter shooter;
    public OuttakePollen(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }
    @Override
    public void execute() {
        shooter.shoot(0.25);
    }
}
