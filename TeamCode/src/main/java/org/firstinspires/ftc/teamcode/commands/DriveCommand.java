package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Drive;

public class DriveCommand extends CommandBase {

    private final Drive drive;

    private final GamepadEx driverOp;
    private int Temp_B_Status;
    private int Temp_X_Status;
    private int Temp_Y_Status;
    private int Temp_A_Status;
    private int Temp_RightBumper;
    private int Temp_Flip;
    private int StartPressed;
    int APressed;
    int BPressed;
    int XPressed;
    int YPressed;
    int RightBumperPressed;
    double Outtake_Speed;

    public DriveCommand(Telemetry telemetry, Drive drive, GamepadEx gamepad1, GamepadEx gamepad2) {

        this.drive = drive;

        this.driverOp = driverOp;
        Temp_B_Status = 0;
        Temp_X_Status = 0;
        Temp_Y_Status = 0;
        Temp_A_Status = 0;
        Temp_RightBumper = 0;
        Temp_Flip = 1;
        StartPressed = 0;
        APressed = 0;
        BPressed = 0;
        XPressed = 0;
        YPressed = 0;
        RightBumperPressed = 0;

        addRequirements(drive);
    }

    @Override
    public void execute() {
        drive.drive(driverOp.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER), driverOp.getRightY(), driverOp.getLeftX());
        if (gamepad1.start) {
            if (gamepad1.start && Temp_Flip == 1 && StartPressed == 0) {
                Temp_Flip = -1;
                StartPressed = 1;
            } else if (gamepad1.start && Temp_Flip == -1 && StartPressed == 0) {
                Temp_Flip = 1;
                StartPressed = 1;
            }
        } else {
            StartPressed = 0;
        }
        FrontWheelLeft.setPower(Temp_Flip * (((gamepad1.left_stick_y - gamepad1.right_stick_x) + gamepad1.left_trigger) - gamepad1.right_trigger));
        FrontWheelRight.setPower(Temp_Flip * (((gamepad1.left_stick_y + gamepad1.right_stick_x) - gamepad1.left_trigger) + gamepad1.right_trigger));
        BackWheelLeft.setPower(Temp_Flip * ((gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_trigger) - gamepad1.right_trigger));
        BackWheelRight.setPower(Temp_Flip * (((gamepad1.left_stick_y - gamepad1.right_stick_x) - gamepad1.left_trigger) + gamepad1.right_trigger));
        if (gamepad2.right_bumper) {
            OuttakeMotorLeft.setPower(0.7);
            OuttakeMotorRight.setPower(0.82);
            Temp_B_Status = 1;
        } else if (gamepad2.left_bumper) {
            OuttakeMotorLeft.setPower(0);
            OuttakeMotorRight.setPower(0);
            BackLeftServo.setPower(0);
            BackRightServo.setPower(0);
            FrontLeftServo.setPower(0);
            FrontRightServo.setPower(0);
            IntakeMotor.setPower(0);
        }
        if (gamepad1.a || gamepad2.a) {
            if ((gamepad1.a || gamepad2.a) && Temp_A_Status == 0 && APressed == 0) {
                BackLeftServo.setPower(1);
                BackRightServo.setPower(1);
                FrontRightServo.setPower(0.3);
                FrontLeftServo.setPower(0.3);
                IntakeMotor.setPower(0.75);
                Temp_A_Status = 1;
                APressed = 1;
            } else if ((gamepad1.a || gamepad2.a) && Temp_A_Status == 1 && APressed == 0) {
                IntakeMotor.setPower(0);
                FrontLeftServo.setPower(0);
                FrontRightServo.setPower(0);
                BackLeftServo.setPower(0);
                BackRightServo.setPower(0);
                APressed = 1;
                Temp_A_Status = 0;

            }
        } else {
            APressed = 0;
        }
        if (gamepad1.b || gamepad2.b) {
            if ((gamepad1.b || gamepad2.b) && Temp_B_Status == 0 && BPressed == 0) {
                OuttakeMotorLeft.setPower(Outtake_Speed);
                OuttakeMotorRight.setPower(Outtake_Speed);
                Temp_B_Status = 1;
                BPressed = 1;
            } else if ((gamepad1.b || gamepad2.b) && Temp_B_Status == 1 && BPressed == 0) {
                OuttakeMotorLeft.setPower(0);
                OuttakeMotorRight.setPower(0);
                BackWheelLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                BackWheelRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                Temp_B_Status = 0;
                BPressed = 1;
            }
        } else {
            BPressed = 0;
        }
        if (gamepad1.x || gamepad2.x) {
            if ((gamepad1.x || gamepad2.x) && Temp_X_Status == 0 && XPressed == 0) {
                FrontLeftServo.setPower(0.3);
                FrontRightServo.setPower(0.3);
                IntakeMotor.setPower(1);
                Temp_X_Status = 1;
                XPressed = 1;
            } else if ((gamepad1.x || gamepad2.x) && Temp_X_Status == 1 && XPressed == 0) {
                FrontLeftServo.setPower(0);
                FrontRightServo.setPower(0);
                IntakeMotor.setPower(0);
                Temp_X_Status = 0;
                XPressed = 1;
            }
        } else {
            XPressed = 0;
        }
        if (gamepad1.y || gamepad2.y) {
            if ((gamepad1.y || gamepad2.y) && Temp_Y_Status == 0 && YPressed == 0) {
                BackLeftServo.setPower(-0.3);
                BackLeftServo.setPower(-0.3);
                Temp_Y_Status = 1;
                YPressed = 1;
            } else if ((gamepad1.y || gamepad2.y) && Temp_Y_Status == 1 && YPressed == 0) {
                BackLeftServo.setPower(0);
                BackRightServo.setPower(0);
                OuttakeMotorLeft.setPower(0.6);
                OuttakeMotorRight.setPower(0.6);
                Temp_Y_Status = 0;
                Temp_B_Status = 1;
                YPressed = 1;
            }
        } else {
            YPressed = 0;
    }

}
