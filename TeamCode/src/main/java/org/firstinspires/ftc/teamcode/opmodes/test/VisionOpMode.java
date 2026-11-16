package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp(name = "VisionOpMode", group = "Test")
public class VisionOpMode extends CommandOpMode {

    private Vision vision;

    /**
     * The IMU, dashboard, telemetry, and vision is being initialized and Telemetry send a message to the robot saying that its initialized.
     */
    @Override
    public void initialize() {
        IMU imu = hardwareMap.get(IMU.class, "imu");

        // Initialize the Dashboard
        FtcDashboard dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addData("Status", "Dashboard Initialized");
        telemetry.update();

        vision = new Vision(hardwareMap);

        register(vision);
    }

    /**
     * The subsystems that were initialized run.
     */

    @Override
    public void run() {
        // super so that periodic in the subsystems also run
        super.run();
        telemetry.addData("Distance from tag (m)", vision.getDistance());
        telemetry.update();
    }

}
