package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

public class RotateTurret extends CommandBase {
    private final Turret turret;

    public RotateTurret(Turret turret) {

        this.turret = turret;

        addRequirements(turret);
    }

    /**
     * The turret moves at a target angle of 0.25 degrees.
     */
     public void execute(){
        turret.setTargetAngle(0.25);
     }
}

