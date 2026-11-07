package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Turret;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp (name = "TestOpMode", group = "Test")
public class TestOpMode extends CommandOpMode {
    private Turret turret;
    private Vision vision;

    @Override
    public void initialize (){
        IMU imu = hardwareMap.get(IMU.class, "imu");
        FtcDashboard dashboard = FtcDashboard.getInstance();

        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        telemetry.addData("Status", "Dashboard Initialized");
        telemetry.update();
        vision = new Vision(hardwareMap, telemetry, imu);
        turret = new Turret(hardwareMap, telemetry, imu);
        register (vision);
        register (turret);
    }

    @Override
    public void run (){
        super.run();
        double tx = vision.gettx();
        turret.trackTarget(tx);
        telemetry.addData("Distance from tag (in)", vision.getDistance());
        telemetry.addData("TX (degrees)", tx);
        telemetry.update();
    }


}
