package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Hood;

public class RotateHood extends CommandBase {
    private final Hood hood;

    /**
     * Creates the object of Hood.
     *
     * @param hood Hood Servo.
     */
    public RotateHood(Hood hood){
        this.hood = hood;

        addRequirements(hood);
    }

    /**
     * The Hood Servo turns at a speed of 25 percent.
     */
    @Override
    public void execute(){
        hood.turn(0.25);
    }
}

