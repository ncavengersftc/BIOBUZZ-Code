package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class TestTurret extends CommandBase {
    private final Turret turret;

    /**
     * Creates an object of TestTurret.
     *
     * @param turret Turret subsystem.
     */
    public TestTurret(Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    /**
     * Manually controls the turret rotation.
     */
    @Override
    public void execute() {
        turret.manualControl(0.2);
    }
}
