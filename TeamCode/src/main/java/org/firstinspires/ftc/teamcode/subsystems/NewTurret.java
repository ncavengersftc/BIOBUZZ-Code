package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class NewTurret extends SubsystemBase {
    private final MotorEx turretMotor;
     public NewTurret(HardwareMap hardwareMap){
         turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
         turretMotor.resetEncoder();
         turretMotor.setRunMode(Motor.RunMode.PositionControl);
         turretMotor.setPositionCoefficient(Constants.TURRET_KP);
         turretMotor.setPositionTolerance(Constants.TURRET_TOLERANCE_TICKS);
         turretMotor.setTargetPosition(Constants.TURRET_MIN_TICKS);
     }

     public void setTargetTicks(int ticks){
         turretMotor.setTargetPosition(ticks);

     }

     @Override
     public void periodic(){
         super.periodic();
         turretMotor.set(0.6);
     }
}
