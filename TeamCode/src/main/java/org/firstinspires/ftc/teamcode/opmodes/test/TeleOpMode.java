package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeTransferPollen;
import org.firstinspires.ftc.teamcode.commands.RotateTurret;
import org.firstinspires.ftc.teamcode.commands.ShootPollen;
import org.firstinspires.ftc.teamcode.commands.StopIntakeTransfer;
import org.firstinspires.ftc.teamcode.commands.StopShooter;
import org.firstinspires.ftc.teamcode.commands.StopTurret;
import org.firstinspires.ftc.teamcode.subsystems.Drive;
import org.firstinspires.ftc.teamcode.subsystems.IntakeTransfer;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Turret;
@TeleOp(name = "TeleOpMode", group = "Main")
public class TeleOpMode extends CommandOpMode {
    private GamepadEx driver;
    private GamepadEx attachment;
    private IntakeTransfer intake;
    private Shooter shooter;
    private Turret turret;
    private Drive drive;

    /**
     * Both gamepads, intake, default commands are being initialized and subsystems are being assigned to a button on the gamepad.
     */

    @Override
    public void initialize(){
        driver = new GamepadEx(gamepad1);
        attachment = new GamepadEx(gamepad2);

        intake = new IntakeTransfer(hardwareMap);
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap);
        drive = new Drive(hardwareMap);

        //register before default commands so that it should work

        register(drive, intake, shooter,turret);

        intake.setDefaultCommand(new StopIntakeTransfer(intake));
        shooter.setDefaultCommand(new StopShooter(shooter));
        turret.setDefaultCommand(new StopTurret(turret));
        drive.setDefaultCommand(new DriveCommand(drive, driver));

        attachment.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakeTransferPollen(intake));

        attachment.getGamepadButton(GamepadKeys.Button.B)
                .toggleWhenPressed(new ShootPollen(shooter));

        attachment.getGamepadButton(GamepadKeys.Button.Y)
                .toggleWhenPressed(new RotateTurret(turret));

    }

    /**
     * Everything that was initialized starts to run.
     */

    @Override
    public void run(){
        super.run();
    }


}
