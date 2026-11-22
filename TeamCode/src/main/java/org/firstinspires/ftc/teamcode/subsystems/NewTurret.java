package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class NewTurret extends SubsystemBase {
    private final MotorEx turretMotor;
    private double targetDegrees = 0;
    private boolean moveFowards = true;
     public NewTurret(HardwareMap hardwareMap){
         turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_117);
         turretMotor.resetEncoder();
         turretMotor.setRunMode(Motor.RunMode.PositionControl);
         turretMotor.setPositionCoefficient(Constants.TURRET_KP);
         turretMotor.setPositionTolerance(Constants.TURRET_TOLERANCE_TICKS);
         turretMotor.setDistancePerPulse(Constants.TURRET_DEGREES_PER_TICK);
         turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
         turretMotor.setTargetDistance(Constants.TURRET_MIN_DEG);
     }

     public void setTargetTicks(int ticks){
         turretMotor.setTargetPosition(Math.max(Constants.TURRET_MIN_TICKS, Math.min(Constants.TURRET_MAX_TICKS,ticks)));
     }

     public void setTargetAngle(double degrees){
         double theoreticalTargetDegrees = targetDegrees + degrees;

         double range = Constants.TURRET_MAX_DEG - Constants.TURRET_MIN_DEG;

         if(theoreticalTargetDegrees > Constants.TURRET_MAX_DEG){
             //Wraps target to be within limits. (ex: 101 --> 1)
             theoreticalTargetDegrees = Constants.TURRET_MIN_DEG +
                     (theoreticalTargetDegrees - Constants.TURRET_MAX_DEG);
             moveFowards = false;
         }
         else if(theoreticalTargetDegrees < Constants.TURRET_MIN_DEG){
             theoreticalTargetDegrees = Constants.TURRET_MAX_DEG -
                     (Constants.TURRET_MIN_DEG - theoreticalTargetDegrees);
             moveFowards = true;
         }
         else {
             moveForwards = theoreticalTargetDegrees >= turretMotor;
         }

         turretMotor.setTargetDistance(Math.max(Constants.TURRET_MIN_DEG, Math.min(Constants.TURRET_MAX_DEG,degrees)));
     }

     public double getCurrentDistance(){
         return turretMotor.getDistance();
     }

     public int getCurrentTicks(){
         return turretMotor.getCurrentPosition();
     }

     public boolean atTarget(){
         return turretMotor.atTargetPosition();
     }

     @Override
     public void periodic(){
         super.periodic();
         turretMotor.setTargetDistance(targetDegrees);
         turretMotor.set(Constants.TURRET_MAX_POWER);
     }
}
