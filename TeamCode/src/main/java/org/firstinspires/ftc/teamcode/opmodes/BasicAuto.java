package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class BasicAuto extends OpMode{
    static final double ticksPerRevolution = 537.7;
    static final double wheelDiameter = 4.0945;
    static final double gearReduction = 1;
    static final double countsPerInch = (ticksPerRevolution * gearReduction) /(wheelDiameter* Math.PI);
    static final int target = (int) Math.round(2.0 *countsPerInch );
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private  DcMotor backRight;
    private DcMotor backLeft;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "Front Left Motor");
        backLeft = hardwareMap.get(DcMotor.class, "Back Left Motor");
        frontRight = hardwareMap.get(DcMotor.class, "Front Right Motor");
        backRight = hardwareMap.get(DcMotor.class, "Back Right Motor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + target);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + target);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + target);
        backRight.setTargetPosition(backRight.getCurrentPosition() + target);
    }
    @Override
    public void start(){
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(0.7);
        backLeft.setPower(0.7);
        frontRight.setPower(0.7);
        backRight.setPower(0.7);
    }
    @Override
    public void loop(){
        if(!frontLeft.isBusy()){
            frontLeft.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            frontRight.setPower(0);
        }
    }
}
