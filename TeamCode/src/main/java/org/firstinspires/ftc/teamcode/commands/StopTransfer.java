package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

public class StopTransfer extends CommandBase {
    private final Transfer transfer;

    /**
     * Creates an object of StopTransfer.
     *
     * @param transfer Transfer subsystem.
     */

    public StopTransfer(Transfer transfer) {

        this.transfer = transfer;

        addRequirements(transfer);
    }

    /**
     * The Transfer servos stop rotating.
     */
    @Override
    public void execute() {
        transfer.stop();
    }
}