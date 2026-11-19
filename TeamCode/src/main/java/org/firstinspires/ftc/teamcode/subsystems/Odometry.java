package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Odometry extends SubsystemBase {
    private final MotorEx horizontalOdometry;
    private final MotorEx verticalOdometry;

    /**
     * Creates an object of Odometry.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Odometry(HardwareMap hardwareMap) {
        horizontalOdometry = new MotorEx(hardwareMap, "Horizontal Odometry");
        verticalOdometry = new MotorEx(hardwareMap, "Vertical Odometry");
    }

    /**
     * Returns current position from the odometry encoders.
     *
     * @return Pose2D, current pose estimate.
     */
    public Pose2D getCurrentPosition() {
        return new Pose2D(
                DistanceUnit.INCH,
                horizontalOdometry.getCurrentPosition(),
                verticalOdometry.getCurrentPosition(),
                AngleUnit.DEGREES,
                0.0
        );
    }
}