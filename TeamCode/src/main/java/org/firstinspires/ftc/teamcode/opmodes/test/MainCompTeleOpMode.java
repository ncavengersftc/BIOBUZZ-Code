package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.IntakePollen;
import org.firstinspires.ftc.teamcode.commands.RotateTurret;
import org.firstinspires.ftc.teamcode.commands.ShootPollen;
import org.firstinspires.ftc.teamcode.commands.StopIntake;
import org.firstinspires.ftc.teamcode.commands.StopShooter;
import org.firstinspires.ftc.teamcode.commands.StopTransfer;
import org.firstinspires.ftc.teamcode.commands.StopTurret;
import org.firstinspires.ftc.teamcode.commands.TransferPollen;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;
import org.firstinspires.ftc.teamcode.subsystems.Turret;
@TeleOp

public class MainCompTeleOpMode extends CommandOpMode {
    private GamepadEx driver;
    private GamepadEx attachment;
    private Intake intake;
    private Shooter shooter;
    private Transfer transfer;
    private Turret turret;
    private Drive drive;

    @Override
    public void initialize(){
        driver = new GamepadEx(gamepad1);
        attachment = new GamepadEx(gamepad2);

        intake = new Intake(hardwareMap);
        intake.setDefaultCommand(new StopIntake(intake));

        shooter = new Shooter(hardwareMap);
        shooter.setDefaultCommand(new StopShooter(shooter));

        transfer = new Transfer(hardwareMap);
        transfer.setDefaultCommand(new StopTransfer(transfer));

        turret = new Turret(hardwareMap);
        turret.setDefaultCommand(new StopTurret(turret));

        register(drive, intake,transfer, shooter,turret);

        drive = new Drive(hardwareMap);
        drive.setDefaultCommand(new DriveCommand(telemetry, drive, driver));

        attachment.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakePollen(intake));

        attachment.getGamepadButton(GamepadKeys.Button.B)
                .toggleWhenPressed(new ShootPollen(shooter));

        attachment.getGamepadButton(GamepadKeys.Button.X)
                .toggleWhenPressed(new TransferPollen(transfer));

        attachment.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new RotateTurret(turret));

    }

    @Override
    public void run(){
        super.run();
    }


}
