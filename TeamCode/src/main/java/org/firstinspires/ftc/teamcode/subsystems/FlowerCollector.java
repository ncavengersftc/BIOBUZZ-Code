package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class FlowerCollector extends SubsystemBase {
    private final CRServo flowerCollectorOne;
    private final CRServo flowerCollectorTwo;

    public FlowerCollector(HardwareMap hardwareMap){
        flowerCollectorOne = hardwareMap.get(CRServo.class, "Flower Collector One");
        flowerCollectorTwo = hardwareMap.get(CRServo.class, "Flower Collector Two");
    }

    public void collect(double power){
        flowerCollectorOne.setPower(power);
        flowerCollectorTwo.setPower(power);
    }

    public void stop(){
        flowerCollectorTwo.setPower(0);
        flowerCollectorTwo.setPower(0);
    }
}
