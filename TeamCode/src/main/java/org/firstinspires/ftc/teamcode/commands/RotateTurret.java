package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class RotateTurret extends CommandBase {
    private final Turret turret;

    public RotateTurret(Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    @Override
    public void execute() {
        turret.setTargetTurns(0.25);
    }
}

