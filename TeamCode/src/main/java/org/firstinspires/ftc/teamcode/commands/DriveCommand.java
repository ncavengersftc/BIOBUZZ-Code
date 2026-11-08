package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drive;

public class DriveCommand extends CommandBase {

    private final Drive drive;

    private final GamepadEx driverOp;
    public DriveCommand(Telemetry telemetry, Drive drive, GamepadEx driverOp) {
        this.drive = drive;

        this.driverOp = driverOp;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.drive(driverOp.getRightX(), driverOp.getRightY(), driverOp.getLeftX());
    }

}