package org.firstinspires.ftc.teamcode.opmodes;

import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Vision;

public class VisionOpMode extends CommandOpMode {

    private Vision vision;

    @Override
    public void initialize() {
        vision = new Vision(hardwareMap);
    }

    @Override
    public void run() {
        telemetry.addData("Distance From Goal", vision.distanceFromGoal());
        telemetry.update();
    }
}
