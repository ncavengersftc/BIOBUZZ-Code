package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "Encoder Test", group = "Test")
public class EncoderTestOpMode extends CommandOpMode {
    private GamepadEx attachment;
    private Intake intake;

    @Override
    public void initialize() {
        attachment = new GamepadEx(gamepad2);
        intake = new Intake(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("Intake Position", intake.getCurrentPosition());
        telemetry.update();
    }
}
