package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.ShootPollen;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;

@TeleOp (name = "ShooterOpMode", group = "Test")
public class ShooterOpMode extends CommandOpMode {
    private Shooter shooter;

    @Override
    public void initialize() {
        shooter = new Shooter(hardwareMap);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());

        telemetry.addData("Status", "Dashboard Initialized");
        telemetry.update();

        shooter.setDefaultCommand(new ShootPollen(shooter));

        register(shooter);
    }

    @Override
    public void run (){
        super.run();
        telemetry.addData("Shooter Velocity", shooter.getAverageVelocity());
        telemetry.update();
    }
}
