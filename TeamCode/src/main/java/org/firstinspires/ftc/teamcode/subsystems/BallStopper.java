package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class BallStopper extends SubsystemBase {
    private final CRServo ballStopperServo;

    /**
     * Creates the object of Tower.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public BallStopper(HardwareMap hardwareMap){
        ballStopperServo = hardwareMap.get(CRServo.class, "Transfer Servo");
    }

    /**
     * Sets the transfer servo power to a specific value.
     *
     * @param power The power at which the transfer servo should run at.
     */
    public void transfer(double power){
        ballStopperServo.setPower(power);
    }

    /**
     * The transfer servo stops rotating.
     */
    public void stop(){
        ballStopperServo.setPower(0);
    }
}
