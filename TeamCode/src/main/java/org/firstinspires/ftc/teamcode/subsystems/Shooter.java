package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Shooter extends SubsystemBase {

    private final MotorEx shooterMotor;

    public Shooter(HardwareMap hMap) {
        shooterMotor = new MotorEx(hMap, "Shooter Motor", 28.0, 6000.0);
        shooterMotor.setRunMode(Motor.RunMode.PositionControl);
    }

    public void shoot (double power){
        shooterMotor.set(power);
    }

    public void stop (){
        shooterMotor.stopMotor();
    }
}


