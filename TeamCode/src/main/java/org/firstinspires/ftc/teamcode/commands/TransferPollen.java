package org.firstinspires.ftc.teamcode.commands;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

public class TransferPollen extends CommandBase {
    private final Transfer transfer;
    public TransferPollen(Transfer transfer) {
        this.transfer = transfer;
        addRequirements(transfer);
    }
    @Override
    public void execute() {
        transfer.turn(0.25);
    }
}
