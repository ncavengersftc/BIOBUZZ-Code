package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Transfer extends SubsystemBase {
    private final CRServo frontLeftServo;
    private final CRServo frontRightServo;
    private final CRServo backLeftServo;


    public Transfer(HardwareMap hardwareMap) {
        frontLeftServo = hardwareMap.get(CRServo.class,"Front Left Servo" );
        frontRightServo = hardwareMap.get(CRServo.class, "Front Right Servo");
        backLeftServo = hardwareMap.get(CRServo.class, "Back Left Servo");

    }

    public void turn(double power) {
        frontLeftServo.setPower(power);
        frontRightServo.setPower(power);
        backLeftServo.setPower(power);
    }

    public void stop() {
        frontRightServo.setPower(0);
        frontLeftServo.setPower(0);
        backLeftServo.setPower(0);
    }

}