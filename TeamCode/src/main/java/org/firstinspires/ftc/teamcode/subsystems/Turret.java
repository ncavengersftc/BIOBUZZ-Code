package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {
    private final MotorEx turretMotor;


    public Turret(HardwareMap hMap) {
        turretMotor = new MotorEx(hMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.setRunMode(Motor.RunMode.PositionControl);

        // set and get the position coefficient
        turretMotor.setPositionCoefficient(0.05);
        double kP = turretMotor.getPositionCoefficient();
        turretMotor.setPositionTolerance(15);
    }

    public void setTargetPosition (int position){
        turretMotor.setTargetPosition(position);

        // perform the control loop
        while (!turretMotor.atTargetPosition()) {
            turretMotor.set(0.75);
        }
        turretMotor.stopMotor(); // stop the motor
    }

    public void stop (){
        turretMotor.stopMotor();
    }

}
