package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drive;

@TeleOp(name = "DriveOpMode", group = "Test")
public class DriveOpMode extends CommandOpMode {

    private Drive drive;

    @Override
    public void initialize() {

        drive = new Drive(hardwareMap);

        GamepadEx driverOp = new GamepadEx(gamepad1);

        // Register BEFORE setting default command or else doesn't work
        register(drive);
        drive.setDefaultCommand(new DriveCommand(telemetry, drive, driverOp));
    }

    @Override
    public void run() {
        super.run();
    }
}