package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.Constants;

import java.util.List;

public class Vision extends SubsystemBase {
    private final Limelight3A limelight;
    public double ty;

    // ty = yaxis of the distance from the camera//
    //tx = xaxis of the distance from the camera//
    //d = distance of the camera from the robot//

    public Vision (HardwareMap hardwareMap){
       limelight = hardwareMap.get(Limelight3A.class, "Limelight Camera");

    }
    public double distanceFromGoal() {
        double degrees = ty + Constants.limelightMountingAngle;
        double radians = degrees * (3.14159 / 180.0);
        return (Constants.goalHeightInches - Constants.limelightHeightInches) / Math.tan(radians);
    }

    @Override
    public void periodic () {
        LLStatus status = limelight.getStatus();
        LLResult result = limelight.getLatestResult();
        ty = result.getTy();
    }


}


