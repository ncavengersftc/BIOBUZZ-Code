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
     * Sweeps the turret back and forth between min and max limits using raw power.
     */
    @Override
    public void execute() {
        turret.sweepPower(0.4);
    }
}
