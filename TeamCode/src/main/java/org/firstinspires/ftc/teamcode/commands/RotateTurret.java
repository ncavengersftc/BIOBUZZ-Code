package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class RotateTurret extends CommandBase {
    private final Turret turret;

    /**
     * Creates an object of RotateTurret.
     *
     * @param turret Turret subsystem.
     */
    public RotateTurret(Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    /**
     * Sweeps the turret back and forth between min and max limits.
     */
    @Override
    public void execute() {
        turret.sweep();
    }
}
