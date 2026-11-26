package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Hood;

public class RotateHood extends CommandBase {
    private final Hood hood;

    public RotateHood(Hood hood){
        this.hood = hood;

        addRequirements(hood);
    }

    @Override
    public void execute(){
        hood.turn(0.25);
    }
}

