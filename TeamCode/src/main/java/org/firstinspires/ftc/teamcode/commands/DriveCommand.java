package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

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
        double turn =
                (driverOp.getButton(GamepadKeys.Button.RIGHT_BUMPER) ? 0.4 : 0)
                        - (driverOp.getButton(GamepadKeys.Button.LEFT_BUMPER) ? 0.4 : 0);

        drive.drive(driverOp.getRightX(), driverOp.getRightY(), turn);
    }

}