package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp(name = "Vision Test", group = "Vision")
public class VisionOpMode extends CommandOpMode {

    private Vision vision;

    private MultipleTelemetry multipleTelemetry;


    @Override
    public void initialize() {
        IMU imu = hardwareMap.get(IMU.class, "imu");

        multipleTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        vision = new Vision(hardwareMap, multipleTelemetry, imu);

        register(vision);
    }

    @Override
    public void run() {
        // super so that periodic in the subsystems also run
        super.run();
        multipleTelemetry.addData("Distance from tag (m)", vision.distanceToTag());
        multipleTelemetry.update();
    }
}
