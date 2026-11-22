package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Transfer;

@Autonomous (name = "Basic Again" , group = "Test Auto")
public class basicagian extends LinearOpMode {

    static final double ticksPerRevolution = 537.7;
    static final double wheelDiameter = 4.0945;
    static final double gearReduction = 1;
    static final double countsPerInch =
            (ticksPerRevolution * gearReduction) / (wheelDiameter * Math.PI);

    @Override
    public void runOpMode() {
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "Front Left Motor");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "Back Left Motor");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "Front Right Motor");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "Back Right Motor");

        Transfer transfer = new Transfer(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        if (isStopRequested()) return;

        int target;

        target = (int) Math.round(15.0 * countsPerInch);
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + target);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + target);
        backRight.setTargetPosition(backRight.getCurrentPosition() + target);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(0.7);
        frontRight.setPower(0.7);
        backLeft.setPower(0.7);
        backRight.setPower(0.7);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            idle();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        shooter.shoot(0.5);
        transfer.turn(0.5);
        sleep(2000);
        shooter.stop();
        transfer.stop();

        target = 945;
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() - target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + target);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - target);
        backRight.setTargetPosition(backRight.getCurrentPosition() + target);

        frontLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            idle();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        target = (int) Math.round(33.5 * countsPerInch);
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + target);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + target);
        backRight.setTargetPosition(backRight.getCurrentPosition() + target);

        frontLeft.setPower(0.7);
        frontRight.setPower(0.7);
        backLeft.setPower(0.7);
        backRight.setPower(0.7);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            idle();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        target = (int) Math.round(-14.3 * countsPerInch);
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() - target);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() - target);
        backRight.setTargetPosition(backRight.getCurrentPosition() + target);

        frontLeft.setPower(0.7);
        frontRight.setPower(0.7);
        backLeft.setPower(0.7);
        backRight.setPower(0.7);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            idle();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        intake.intake(0.5);

        target = (int) Math.round(13.8 * countsPerInch);
        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + target);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + target);
        backRight.setTargetPosition(backRight.getCurrentPosition() + target);

        frontLeft.setPower(0.7);
        frontRight.setPower(0.7);
        backLeft.setPower(0.7);
        backRight.setPower(0.7);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()) {
            idle();
        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        shooter.stop();
        transfer.stop();
        intake.stop();
    }
}