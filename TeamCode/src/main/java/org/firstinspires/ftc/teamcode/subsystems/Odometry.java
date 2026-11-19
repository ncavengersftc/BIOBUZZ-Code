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

    private static final double horizontalInchesPerPulse = 2.0;
    private static final double verticalInchesPerPulse = 2.0;

    // Physical pod offsets in inches from the robot's center.
    private final double forwardOffset; 
    private final double strafeOffset;

    /**
     * Creates an object of Odometry.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */
    public Odometry(HardwareMap hardwareMap, double forwardOffset, double strafeOffset) {
        horizontalOdometry = new MotorEx(hardwareMap, "Horizontal Odometry");
        verticalOdometry = new MotorEx(hardwareMap, "Vertical Odometry");

        horizontalOdometry.setDistancePerPulse(horizontalInchesPerPulse);
        verticalOdometry.setDistancePerPulse(verticalInchesPerPulse);

        this.forwardOffset = forwardOffset;
        this.strafeOffset = strafeOffset;

    }

    /**
     * Returns current position from the odometry encoders in inches.
     *
     * @return Pose2D, current pose estimate.
     */
    public Pose2D getCurrentPosition() {
        return new Pose2D(
                DistanceUnit.INCH,
                horizontalOdometry.getDistance(),
                verticalOdometry.getDistance(),
                AngleUnit.DEGREES,
                0.0
        );
    }

    /**
     * Resets both odometry encoders.
     */
    public void resetEncoders() {
        horizontalOdometry.resetEncoder();
        verticalOdometry.resetEncoder();
    }
}
