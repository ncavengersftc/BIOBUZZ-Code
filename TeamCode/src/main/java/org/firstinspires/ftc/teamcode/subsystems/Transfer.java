package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Transfer extends SubsystemBase {
    private final CRServo transferServo;

    public Transfer(HardwareMap hardwareMap){
        transferServo = hardwareMap.get(CRServo.class, "Transfer Servo");
    }

    public void turn(double power){
        transferServo.setPower(power);

    }

    public void stop(){
        transferServo.setPower(0);
    }

}