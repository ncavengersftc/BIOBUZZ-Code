package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

@TeleOp(name = "TurretEncoderTest")
public class TurretEncoderTest extends LinearOpMode {

    private MotorEx turretMotor;

    @Override
    public void runOpMode() {

        turretMotor = new MotorEx(hardwareMap, "Turret Motor", MotorEx.GoBILDA.RPM_312);

        waitForStart();

        while (opModeIsActive()) {
            int position = turretMotor.getCurrentPosition();

            telemetry.addData("Encoder Position", position);
            telemetry.update();
        }
    }
}