package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Hood extends SubsystemBase {
    private final CRServo hoodServo;

    /**
     * Creates the object of Hood.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Hood(HardwareMap hardwareMap) {
        hoodServo = hardwareMap.get(CRServo.class, "Hood Servo");
    }

    /**
     * Sets the hood servo's power to the value, can control its direction and speed of rotation.
     *
     * @param power The power to send to the hood servo.
     */

    public void turn(double power) {
        hoodServo.setPower(power);
    }

    /**
     * The Hood stops moving
     */

    public void stop() {
        hoodServo.setPower(0);
    }
}
