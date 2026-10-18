package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {
    private final MotorEx shooterMotor;


    public Turret(HardwareMap hMap) {
        shooterMotor = new MotorEx(hMap, "Shooter Motor", 28.0, 6000);
        shooterMotor.setRunMode(Motor.RunMode.PositionControl);

        // set and get the position coefficient
        shooterMotor.setPositionCoefficient(0.05);
        double kP = shooterMotor.getPositionCoefficient();
        shooterMotor.setPositionTolerance(15);
    }

    public void setTargetPosition (int position){
        shooterMotor.setTargetPosition(position);

        // perform the control loop
        while (!shooterMotor.atTargetPosition()) {
            shooterMotor.set(0.75);
        }
        shooterMotor.stopMotor(); // stop the motor
    }

    public void stop (){
        shooterMotor.stopMotor();
    }

}
