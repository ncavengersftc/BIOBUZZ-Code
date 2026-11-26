package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.FlowerCollector;

public class FlowerCollectPollen extends CommandBase {
    private final FlowerCollector flowerCollector;

    public FlowerCollectPollen(FlowerCollector flowerCollector){
        this.flowerCollector = flowerCollector;

        addRequirements(flowerCollector);
    }

    @Override
    public void execute(){
        flowerCollector.collect(0.25);
    }
}

