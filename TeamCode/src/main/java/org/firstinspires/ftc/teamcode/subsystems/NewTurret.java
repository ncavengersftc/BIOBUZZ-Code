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
         turretMotor.setDistancePerPulse(Constants.TURRET_DEGREES_PER_TICK);
         turretMotor.setTargetDistance(Constants.TURRET_MIN_DEG);
     }

     public void setTargetTicks(int ticks){
         turretMotor.setTargetPosition(Math.max(Constants.TURRET_MIN_TICKS, Math.min(Constants.TURRET_MAX_TICKS,ticks)));
     }

     public void setTargetAngle(double degrees){
         turretMotor.setTargetDistance(Math.max(Constants.TURRET_MIN_DEG, Math.min(Constants.TURRET_MAX_DEG,degrees)));
     }

     @Override
     public void periodic(){
         super.periodic();
         turretMotor.set(Constants.TURRET_MAX_POWER);
     }
}
