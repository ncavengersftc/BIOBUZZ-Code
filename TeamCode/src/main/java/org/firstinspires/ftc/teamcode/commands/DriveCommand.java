package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class tssotuffboiOFFICIAL extends LinearOpMode {

    private DcMotor FrontWheelLeft;
    private DcMotor BackWheelLeft;
    private DcMotor FrontWheelRight;
    private DcMotor BackWheelRight;
    private CRServo BackLeftServo;
    private CRServo FrontLeftServo;
    private CRServo BackRightServo;
    private CRServo FrontRightServo;
    private DcMotor IntakeMotor;
    private DcMotor OuttakeMotorLeft;
    private DcMotor OuttakeMotorRight;


    @Override
    public void runOpMode() {
        int Temp_B_Status;
        int Temp_X_Status;
        int Temp_Y_Status;
        int Temp_A_Status;
        int Temp_RightBumper;
        int Temp_Flip;
        int StartPressed;
        int APressed;
        int BPressed;
        int XPressed;
        int YPressed;
        int RightBumperPressed;
        double Outtake_Speed;

        FrontWheelLeft = hardwareMap.get(DcMotor.class, "Front Wheel Left");
        BackWheelLeft = hardwareMap.get(DcMotor.class, "Back Wheel Left");
        FrontWheelRight = hardwareMap.get(DcMotor.class, "Front Wheel Right");
        BackWheelRight = hardwareMap.get(DcMotor.class, "Back Wheel Right");
        BackLeftServo = hardwareMap.get(CRServo.class, "Back Left Servo");
        FrontLeftServo = hardwareMap.get(CRServo.class, "Front Left Servo");
        BackRightServo = hardwareMap.get(CRServo.class, "BackRightServoAsCRServo");
        FrontRightServo = hardwareMap.get(CRServo.class, "Front Right Servo");
        IntakeMotor = hardwareMap.get(DcMotor.class, "Intake Motor");
        OuttakeMotorLeft = hardwareMap.get(DcMotor.class, "Outtake Motor Left");
        OuttakeMotorRight = hardwareMap.get(DcMotor.class, "Outtake Motor Right");

        FrontWheelLeft.setDirection(DcMotor.Direction.FORWARD);
        BackWheelLeft.setDirection(DcMotor.Direction.FORWARD);
        FrontWheelRight.setDirection(DcMotor.Direction.REVERSE);
        BackWheelRight.setDirection(DcMotor.Direction.REVERSE);
        BackLeftServo.setDirection(CRServo.Direction.REVERSE);
        FrontLeftServo.setDirection(CRServo.Direction.REVERSE);
        BackRightServo.setDirection(CRServo.Direction.FORWARD);
        FrontRightServo.setDirection(CRServo.Direction.FORWARD);
        IntakeMotor.setDirection(DcMotor.Direction.REVERSE);
        OuttakeMotorLeft.setDirection(DcMotor.Direction.REVERSE);
        OuttakeMotorRight.setDirection(DcMotor.Direction.REVERSE);
        BackWheelLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackWheelRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontWheelLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontWheelRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        IntakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        OuttakeMotorLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        OuttakeMotorRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
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
        Outtake_Speed = 0.67;
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
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
        }
    }
}