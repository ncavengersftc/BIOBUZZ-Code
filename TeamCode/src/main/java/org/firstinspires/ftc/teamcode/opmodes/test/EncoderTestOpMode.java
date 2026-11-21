package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.commands.IntakePollen;
import org.firstinspires.ftc.teamcode.commands.RotateTurret;
import org.firstinspires.ftc.teamcode.commands.StopIntake;
import org.firstinspires.ftc.teamcode.commands.StopTurret;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Turret;

@TeleOp(name = "Encoder Test", group = "Test")
public class EncoderTestOpMode extends CommandOpMode {
    private GamepadEx attachment;
    private Intake intake;
    private Turret turret;

    @Override
    public void initialize() {
        attachment = new GamepadEx(gamepad2);
        intake = new Intake(hardwareMap);
        turret = new Turret(hardwareMap);

        intake.setDefaultCommand(new StopIntake(intake));
        attachment.getGamepadButton(GamepadKeys.Button.A)
                .toggleWhenPressed(new IntakePollen(intake));

        turret.setDefaultCommand(new StopTurret(turret));
        attachment.getGamepadButton(GamepadKeys.Button.B)
                        .toggleWhenPressed(new RotateTurret(turret));
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("Turret Encoder", turret.getCurrentPosition());
        telemetry.update();
    }
}
