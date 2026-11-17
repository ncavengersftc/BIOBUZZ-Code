package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drive;


public class DriveCommand extends CommandBase {

    private final Drive drive;

    private final GamepadEx driverOp;

    /**
     * Creates an object of DriveCommand.
     *
     * @param drive Drive subsystem.
     * @param driver Gamepad 1 operator.
     */
    public DriveCommand(Drive drive, GamepadEx driver) {
        this.drive = drive;

        this.driverOp = driver;

        addRequirements(drive);
    }

    /**
     * The Triggers turn (robot centric), and Left joystick strafes, while the right joystick moves forwards/backward.
     */

    @Override
    public void execute() {
        double turn =
                driverOp.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER)
                        - driverOp.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);

        drive.drive(-driverOp.getLeftX(), driverOp.getRightY(), turn);
    }

}