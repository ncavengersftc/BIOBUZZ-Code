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
     * Oscillates turret between min and max limits, automatically reversing direction at each limit.
     */
    @Override
    public void execute() {
        turret.sweep();
    }
}
