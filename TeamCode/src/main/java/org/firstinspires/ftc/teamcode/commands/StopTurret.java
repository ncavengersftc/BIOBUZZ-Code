package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class StopTurret extends CommandBase {
    private final Turret turret;

    /**
     * Creates an object of StopTurret.
     *
     * @param turret Turret subsystem.
     */
    public StopTurret(Turret turret) {

        this.turret = turret;

        addRequirements(turret);
    }

    /**
     * The turret stops rotating.
     */
    @Override
    public void execute() {
        turret.stop();
    }
}
