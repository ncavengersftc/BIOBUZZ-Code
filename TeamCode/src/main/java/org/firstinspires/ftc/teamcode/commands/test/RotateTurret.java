package org.firstinspires.ftc.teamcode.commands.test;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class RotateTurret extends CommandBase {
    private final Turret turret;
    public RotateTurret(Shooter shooter) {
        this.turret = new Turret();
        addRequirements(shooter);
    }
    @Override
    public void execute() {
        turret.setTargetPosition(0.25);
    }
}

