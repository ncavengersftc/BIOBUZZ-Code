package org.firstinspires.ftc.teamcode.opmodes.test;

import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Tower;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;
import org.firstinspires.ftc.teamcode.subsystems.Turret;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

public class FullVisionOpMode extends CommandOpMode {
    private Drive drive;
    private Turret turret;
    private Vision vision;
    private Intake intake;
    private Shooter shooter;
    private Transfer transfer;
    private Tower tower;

    @Override
    public void initialize() {
        drive = new Drive(hardwareMap);
        turret = new Turret(hardwareMap);
        vision = new Vision(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        transfer = new Transfer(hardwareMap);
        tower = new Tower(hardwareMap);
    }

    @Override
    public void run() {

        // GAMEPAD 1: DRIVE
        double turn = (gamepad1.right_bumper ? 0.5 : 0) - (gamepad1.left_bumper ? 0.5 : 0);
        drive.drive(gamepad1.right_stick_x, -gamepad1.left_stick_y, turn);

        // GAMEPAD 2: INTAKE
        if (gamepad2.a) intake.intake(0.5);
        else if (gamepad2.b) intake.intake(-0.5);
        else intake.stop();

        // TRANSFER
        if (gamepad2.x) transfer.turn(0.5);
        else transfer.stop();

        // TOWER
        if (gamepad2.y) tower.transfer(0.5);
        else tower.stop();

        // SHOOTER
        if (gamepad2.right_bumper) shooter.shoot(1500);
        else shooter.stop();

        // LIMELIGHT TURRET
        double tx = vision.getTx();

        if (gamepad2.left_bumper && Double.isFinite(tx)) {
            turret.trackTarget(tx);
        }

        telemetry.addData("TX", tx);
        telemetry.addData("Distance", vision.getDistance());
        telemetry.addData("Target Visible", Double.isFinite(tx));

        super.run();
        telemetry.update();
    }
}
