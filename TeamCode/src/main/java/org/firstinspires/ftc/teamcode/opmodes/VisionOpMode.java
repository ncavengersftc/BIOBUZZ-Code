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

    private IMU imu;

    private MultipleTelemetry telemetry;


    @Override
    public void initialize() {
        imu = hardwareMap.get(IMU.class, "imu");

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        vision = new Vision(hardwareMap, telemetry, imu);

        register(vision);
    }

    @Override
    public void run() {
        // super so that periodic in the subsystems also run
        super.run();
        telemetry.addData("Distance from tag (m)", vision.distanceToTag());
        telemetry.update();
    }
}
