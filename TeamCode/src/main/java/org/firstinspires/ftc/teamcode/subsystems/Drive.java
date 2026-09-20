package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drive extends SubsystemBase {
    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor backLeft;
    private final Motor backRight;

    private final MecanumDrive drive;

    private final Telemetry telemetry;

    public Drive(Telemetry telemetry, HardwareMap hardwareMap) {
        frontLeft = new Motor(hardwareMap, "Front Left Wheel");
        frontRight = new Motor(hardwareMap, "Front Right Wheel");
        backLeft = new Motor(hardwareMap, "Back Left Wheel");
        backRight = new Motor(hardwareMap, "Back Right Wheel");

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

        this.telemetry = telemetry;
    }

    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }
}
