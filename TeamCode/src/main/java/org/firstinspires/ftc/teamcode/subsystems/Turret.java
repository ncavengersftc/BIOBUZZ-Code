package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class Turret extends SubsystemBase {
    private final MotorEx turretMotor;
    //this is the set point of the turret
    private double targetDegrees = 0;
    private boolean moveForwards = true;
     public Turret(HardwareMap hardwareMap){
         turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_117);
         turretMotor.resetEncoder();
         turretMotor.setRunMode(Motor.RunMode.PositionControl);
         turretMotor.setPositionCoefficient(Constants.TURRET_KP);
         turretMotor.setPositionTolerance(Constants.TURRET_TOLERANCE_TICKS);
         turretMotor.setDistancePerPulse(Constants.TURRET_DEGREES_PER_TICK);
         turretMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
         turretMotor.setTargetDistance(Constants.TURRET_MIN_DEG);
     }


     public void setTargetAngle(double degrees) {
         //theoretical target degrees is the degrees that you want it at to get to plus the degrees inputed.
         double theoreticalTargetDegrees = targetDegrees + degrees;

         double range = Constants.TURRET_MAX_DEG - Constants.TURRET_MIN_DEG;

         //if the turret reaches more than the max, it just sets the max degrees as that.
         if (theoreticalTargetDegrees > Constants.TURRET_MAX_DEG) {
             //Wraps target to be within limits. (ex: 101 --> 1)
             /*
             theoreticalTargetDegrees = Constants.TURRET_MIN_DEG +
                     (theoreticalTargetDegrees - Constants.TURRET_MAX_DEG);
              */
             theoreticalTargetDegrees = Constants.TURRET_MAX_DEG;

         }
         //if the turret reaches less than the min, it just sets the min degrees as that.
         else if (theoreticalTargetDegrees < Constants.TURRET_MIN_DEG) {
           /*
           theoreticalTargetDegrees = Constants.TURRET_MAX_DEG -
                     (Constants.TURRET_MIN_DEG - theoreticalTargetDegrees);
            */
             theoreticalTargetDegrees = Constants.TURRET_MIN_DEG;
         }

         //if the turret position distance is true, it moves forward, if not, it becomes false
         moveForwards = theoreticalTargetDegrees >= turretMotor.getDistance();
         targetDegrees = theoreticalTargetDegrees;
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
     //target degrees
     public void trackTarget (double tx){
         if(!Double.isFinite(tx) && Math.abs(tx) < Constants.TURRET_VISION_TOLERANCE_DEG){
             return;
         }
         targetDegrees = targetDegrees + tx;
     }

     public void stop(){
         turretMotor.stopMotor();
     }

     @Override
     public void periodic(){
         super.periodic();
         turretMotor.setTargetDistance(targetDegrees);
         // actually gives power to the motors so it can get to set point
         //boolean ternary function, what happens when move forwards is true, what happens when its false
         turretMotor.set((moveForwards) ? Constants.TURRET_MAX_POWER : -Constants.TURRET_MAX_POWER);
     }
}
