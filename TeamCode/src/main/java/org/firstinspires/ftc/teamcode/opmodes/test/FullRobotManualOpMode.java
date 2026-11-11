package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name = "Full Robot Manual Test")
public class FullRobotManualOpMode extends CommandOpMode {

    private Drive drive;
    private Turret turret;
    private Intake intake;
    private Shooter shooter;
    private Transfer transfer;
    private Tower tower;

    private double targetAngle = 0;
    private double lastTime;

    @Override
    public void initialize() {
        drive = new Drive(hardwareMap);
        turret = new Turret(hardwareMap);
        intake = new Intake(hardwareMap);
        shooter = new Shooter(hardwareMap);
        transfer = new Transfer(hardwareMap);
        tower = new Tower(hardwareMap);

        lastTime = getRuntime();
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

        // MANUAL TURRET
        double now = getRuntime();
        double dt = now - lastTime;
        lastTime = now;

        double turnTurret = gamepad2.right_trigger - gamepad2.left_trigger;

        if (Math.abs(turnTurret) > 0.01) {
            targetAngle += turnTurret * 90 * dt;
            targetAngle = Math.max(-100, Math.min(100, targetAngle));
            turret.setTargetAngle(targetAngle);
        }

        super.run();
    }
}