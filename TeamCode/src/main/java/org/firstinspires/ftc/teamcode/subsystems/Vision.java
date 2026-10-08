package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

public class Vision extends SubsystemBase {

    private final Limelight3A limelight;

    // Robot gyroscope (called IMU)
    private final IMU imu;

    private final Telemetry telemetry;

    // Robot position on 3D field
    private Pose3D pose;

    private LLResult result;

    // Yaw (heading) of the robot
    private double currentYaw;

    // Distance to the tag
    private double distanceFromTag;


    public Vision (HardwareMap hardwareMap, Telemetry telemetry){
       this.telemetry = telemetry;

       limelight = hardwareMap.get(Limelight3A.class, "limelight");
       imu = hardwareMap.get(IMU.class, "imu");

       // Initializes IMU
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));
        imu.initialize(parameters);

       // Request data at 100Hz and start the camera processing
       limelight.setPollRateHz(100);
       limelight.start();

    }

    private void updateLimelightValues() {
        // Robots yaw (angle it is facing) from IMU
        currentYaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        // Give yaw to limelight so it can calculate position accurately
        limelight.updateRobotOrientation(currentYaw);

        // Gets latest limelight result
        result = limelight.getLatestResult();
    }

    private void updatePose() {
        pose = result.getBotpose_MT2();

        /*
        if (pose != null) {
            telemetry.addData("MT2 Pose X", pose.getPosition().x);
            telemetry.addData("MT2 Pose Y", pose.getPosition().y);
            telemetry.addData("MT2 Yaw", currentYaw);
        }
        */
    }

    private void updateDistance() {
        // Fetch the list of all tags currently in view
        List<LLResultTypes.FiducialResult> tags = result.getFiducialResults();

        // If the limelight result is valid
        if (!tags.isEmpty()) {
            // Grab the first tag the camera sees
            LLResultTypes.FiducialResult firstTag = tags.get(0);

            // Get the pose of the target relative to the camera
            Pose3D targetPose = firstTag.getTargetPoseCameraSpace();

            if (targetPose != null) {
                // Extract the X, Y, and Z translations in meters
                double x = targetPose.getPosition().x;
                double y = targetPose.getPosition().y;
                double z = targetPose.getPosition().z;

                // Calculate the distance in meters
                distanceFromTag = Math.sqrt((x * x) + (y * y) + (z * z));
            }
        }
    }

    public double distanceToTag() {
        return distanceFromTag;
    }

    @Override
    public void periodic () {
        // Updates the yaw and gets current limelight result
        updateLimelightValues();

        // If limelight sees a tag
        if (result != null && result.isValid()) {
            // Gets robot position
            updatePose();

            // Updates distance from tag
            updateDistance();
        }

        // If no tags are visible
        else {
            telemetry.addData("Vision", "No tags in view");
        }
    }
}


