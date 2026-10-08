package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Vision;

@TeleOp(name = "Vision Test", group = "Vision")
public class VisionOpMode extends CommandOpMode {

    private Vision vision;

    @Override
    public void initialize() {
        vision = new Vision(hardwareMap, telemetry);

        register(vision);
    }

    @Override
    public void run() {
        // super so that periodic in the subsystems also run
        super.run();
        telemetry.addData("Distance from tag (m)", vision.distanceToTag());
        telemetry.update();
    }
}
