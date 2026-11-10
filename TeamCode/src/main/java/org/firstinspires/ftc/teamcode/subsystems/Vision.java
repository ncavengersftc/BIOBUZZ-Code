package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Vision extends SubsystemBase {
    private final Limelight3A limelight;

    private static final double mountAngle = 25.0; // Camera mounting angle (degrees)
    private static final double heightFromGround = 20.0; // Camera height (inches)
    private static final double heightOfTarget = 60.0; // Target height (inches)


    public Vision(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight");
        limelight.pipelineSwitch(0);
        limelight.start();

    }

    public double getDistance() {
        LLResult result = limelight.getLatestResult();

        if (result == null || !result.isValid()) {
            return Double.NaN;
        }

        // The angle to the april tags
        double limelightToTargetDegrees = result.getTy();

        // Calculate angle to target
        double angleToGoalDegrees = mountAngle + limelightToTargetDegrees;

        // Convert degrees to radians
        double angleToGoalRadians = Math.toRadians(angleToGoalDegrees);

        // Calculate horizontal distance in inches
        return (heightOfTarget - heightFromGround) / Math.tan(angleToGoalRadians);
    }

    public double getTx() {
        LLResult result = limelight.getLatestResult();

        if (result == null || !result.isValid() || Double.isInfinite(result.getTx())) {
            return Double.NaN;
        }

        return result.getTx();
    }

}

