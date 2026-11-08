package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Turret;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp (name = "TurretOpMode", group = "Test")
public class TurretOpMode extends CommandOpMode {
    private Turret turret;
    private Vision vision;

    @Override
    public void initialize() {
        IMU imu = hardwareMap.get(IMU.class, "imu");

        vision = new Vision(hardwareMap);
        turret = new Turret(hardwareMap);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addData("Status", "Dashboard Initialized");
        telemetry.update();

        register(turret);
    }

    @Override
    public void run (){
        super.run();
        turret.trackTarget(vision.getTx());
        telemetry.addData("Turret Turns", turret.getTurns());
        telemetry.update();
    }
}
