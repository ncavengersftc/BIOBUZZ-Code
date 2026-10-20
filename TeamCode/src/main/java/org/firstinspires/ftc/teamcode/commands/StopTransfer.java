package org.firstinspires.ftc.teamcode.commands;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

public class StopTransfer extends CommandBase {
    private final Transfer transfer;
    public StopTransfer(Transfer transfer) {
        this.transfer = transfer;
        addRequirements(transfer);
    }
    @Override
    public void execute() {
        transfer.stop();
    }
}