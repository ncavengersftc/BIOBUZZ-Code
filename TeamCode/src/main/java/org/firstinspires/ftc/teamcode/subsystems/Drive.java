package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Drive extends SubsystemBase {
    private final MotorEx frontLeft;
    private final MotorEx frontRight;
    private final MotorEx backLeft;
    private final MotorEx backRight;

    private final MecanumDrive drive;

    /**
     * Creates an object of Drive.
     *
     * @param hardwareMap Connects java code to the robots physical hardware.
     */

    public Drive(HardwareMap hardwareMap) {
        frontLeft = new MotorEx(hardwareMap, "Front Left Motor", Motor.GoBILDA.RPM_312);
        frontRight = new MotorEx(hardwareMap, "Front Right Motor", Motor.GoBILDA.RPM_312);
        backLeft = new MotorEx(hardwareMap, "Back Left Motor", Motor.GoBILDA.RPM_312);
        backRight = new MotorEx(hardwareMap, "Back Right Motor", Motor.GoBILDA.RPM_312);

        drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    /**
     * The robot can strafe, move forward/backward, and turn robot centric.
     *
     * @param strafeSpeed The speed at which the robot can strafe, which is to move side to side.
     * @param forwardSpeed The speed at which the robot can move forward and backwards.
     * @param turnSpeed The speed at which the robot can turn.
     */
    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        drive.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }


}

