package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Tower extends SubsystemBase {
    private final CRServo transferServo;

    /**
     * Creates the object of Tower.
     *
     * @param hardwareMap (connects java code to the robots physical hardware).
     */
    public Tower(HardwareMap hardwareMap){
        transferServo = hardwareMap.get(CRServo.class, "Transfer Servo");
    }

    /**
     * Sets the transfer servo power to a specific value.
     *
     * @param power (the power at which the transfer servo should run at).
     */
    public void transfer(double power){
        transferServo.setPower(power);
    }

    /**
     * The transfer servo stops rotating.
     */
    public void stop(){
        transferServo.setPower(0);
    }
}
