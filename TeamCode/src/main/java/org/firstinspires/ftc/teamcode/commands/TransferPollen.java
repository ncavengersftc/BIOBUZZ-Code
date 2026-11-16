package org.firstinspires.ftc.teamcode.commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

public class TransferPollen extends CommandBase {
    private final Transfer transfer;

    /**
     * Creates an object of TransferPollem
     * @param transfer
     */
    public TransferPollen(Transfer transfer) {

        this.transfer = transfer;

        addRequirements(transfer);
    }

    /**
     * The transfer servos run at a speed of 25 percent.
     */
    @Override
    public void execute() {
        transfer.turn(0.25);
    }
}
