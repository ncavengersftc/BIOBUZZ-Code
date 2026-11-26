package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class FlowerCollector extends SubsystemBase {
    private final CRServo flowerCollectorOne;
    private final CRServo flowerCollectorTwo;

    /**
     * Creates the object of Flower Collector.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public FlowerCollector(HardwareMap hardwareMap){
        flowerCollectorOne = hardwareMap.get(CRServo.class, "Flower Collector One");
        flowerCollectorTwo = hardwareMap.get(CRServo.class, "Flower Collector Two");
    }

    /**
     *  Sets the Flower collector servo's power to a specific value.
     *
     * @param power Power of the flower collector servos.
     */
    public void collect(double power){
        flowerCollectorOne.setPower(power);
        flowerCollectorTwo.setPower(power);
    }

    /**
     * Stops the flower Collector servos.
     */
    public void stop(){
        flowerCollectorTwo.setPower(0);
        flowerCollectorTwo.setPower(0);
    }
}

