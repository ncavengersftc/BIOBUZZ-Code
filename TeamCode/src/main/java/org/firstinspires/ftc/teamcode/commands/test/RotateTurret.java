package org.firstinspires.ftc.teamcode.commands.test;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class RotateTurret extends CommandBase {
    private final Turret turret;
    public RotateTurret(Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }
    @Override
    public void execute() {
        turret.setTargetPosition(0.25);
    }
}

