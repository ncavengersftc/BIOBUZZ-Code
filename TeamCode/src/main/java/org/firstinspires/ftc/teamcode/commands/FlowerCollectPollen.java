package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.FlowerCollector;

public class FlowerCollectPollen extends CommandBase {
    private final FlowerCollector flowerCollector;

    /**
     * Creates an object of Flower Collector.
     *
     * @param flowerCollector Flower Collector subsystem.
     */
    public FlowerCollectPollen(FlowerCollector flowerCollector){
        this.flowerCollector = flowerCollector;

        addRequirements(flowerCollector);
    }

    /**
     * The Flower Collector collects the pollen at a speed of 25 percent.
     */
    @Override
    public void execute(){
        flowerCollector.collect(0.25);
    }
}

