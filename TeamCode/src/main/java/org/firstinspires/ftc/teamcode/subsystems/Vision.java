package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Vision extends SubsystemBase {
    private final Limelight3A limelight;

    private static final double a1 = 25.0; // Camera mounting angle (degrees)
    private static final double h1 = 20.0; // Camera height (inches)
    private static final double h2 = 60.0; // Target height (inches)


    public Vision(HardwareMap hardwareMap, Telemetry telemetry, IMU imu) {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

    }

    public double getDistance() {
        LLResult result = limelight.getLatestResult();

        if (result == null || !result.isValid()) {
            return Double.NaN;
        }

        double targetOffsetAngle_Vertical = result.getTy();
        // Calculate angle to target
        double angleToGoalDegrees =
                a1 + targetOffsetAngle_Vertical;

        // Convert degrees to radians
        double angleToGoalRadians =
                Math.toRadians(angleToGoalDegrees);

        // Calculate horizontal distance in inches
        double distanceToTag =
                (h2 - h1) / Math.tan(angleToGoalRadians);

        return distanceToTag;
    }
}

