package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;
@TeleOp (name = "SubsystemTestOpMode", group = "Test")
public class SubsystemTestOpMode extends CommandOpMode {
    private Intake intake;
    private Shooter shoot;
    private Transfer transfer;
    private GamepadEx gamepad1;

    @Override
    public void initialize(){
        intake = new Intake(hardwareMap);
        shoot = new Shooter(hardwareMap);
        transfer = new Transfer(hardwareMap);

        // Register subsystems
        register(intake);
        register(shoot);
        register(transfer);
    }

    @Override
    public void run() {
        super.run();
    }
}


