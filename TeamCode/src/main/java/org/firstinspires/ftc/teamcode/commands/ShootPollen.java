package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
public class ShootPollen extends CommandBase {
    private final Shooter shooter;

    /**
     * Creates an object of ShootPollen.
     *
     * @param shooter Shooter subsystem.
     */

    public ShootPollen(Shooter shooter) {

        this.shooter = shooter;

        addRequirements(shooter);
    }

    /**
     * Shooter shoots the pollen at 25 rotations per second.
     */
    @Override
    public void execute() {
        shooter.shoot(25);
    }
}
