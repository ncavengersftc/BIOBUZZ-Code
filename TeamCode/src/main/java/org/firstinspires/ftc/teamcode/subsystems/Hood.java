package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Hood extends SubsystemBase {
    private final CRServo hoodServo;

    public Hood(HardwareMap hardwareMap) {
        hoodServo = hardwareMap.get(CRServo.class, "Hood Servo");
    }

    public void turn(double power) {
        hoodServo.setPower(power);
    }

    public void stop() {
        hoodServo.setPower(0);
    }
}
