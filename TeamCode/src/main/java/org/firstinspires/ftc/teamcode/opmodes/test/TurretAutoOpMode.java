package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Turret;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp (name = "TurretOpMode", group = "Test")
public class TurretAutoOpMode extends CommandOpMode {
    private Turret turret;
    private Vision vision;

    /**
     * IMU,vision,turret,telemetry,dashboard is being initialized.
     */

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

    /**
     * The turret attempts to track the target using the limelight x angle offset based on the April tag and telemetry returns the degrees of the turret degrees and the angle.
     */
    @Override
    public void run (){
        super.run();
        turret.trackTarget(vision.getTx());
        telemetry.addData("Turret Degrees", turret.getAngle());
        telemetry.update();
    }
}
