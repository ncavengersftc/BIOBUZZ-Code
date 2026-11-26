package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class Odometry extends SubsystemBase {
    private final GoBildaPinpointDriver pinpoint;

    /**
     * @param hardwareMap   Creates an Odometry object
     * @param xPodOffset    How far left (+) or right (-) of robot center the forward (X) pod is, in inches
     * @param yPodOffset    How far forward (+) or backward (-) of robot center the strafe (Y) pod is, in inches
     */
    public Odometry(HardwareMap hardwareMap, double xPodOffset, double yPodOffset) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

        // Convert inch offsets to mm for Pinpoint configuration
        pinpoint.setOffsets(xPodOffset, yPodOffset, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

        // Flip either to REVERSED if forward/left movement reports negative values
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );

        pinpoint.resetPosAndIMU();
    }

    /**
     * The odometry gets the current position.
     *
     * @return the Odometry's current position.
     */
    public Pose2D getCurrentPosition() {
        return pinpoint.getPosition();
    }

    /**
     *
     *The Odometry encoders reset.
     */
    public void resetEncoders() {
        pinpoint.resetPosAndIMU();
    }

    /**
     * The pinpoint updates every periodic time frame.
     */
    @Override
    public void periodic() {
        // Must be called once per loop to fetch fresh sensor-fused pose data
        pinpoint.update();
    }
}

