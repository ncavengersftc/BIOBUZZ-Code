package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {
    private final MotorEx turretMotor;


    public Turret() {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.setRunMode(Motor.RunMode.PositionControl);

        // set and get the position coefficient
        turretMotor.setPositionCoefficient(0.05);
        turretMotor.setPositionTolerance(15);
    }

    public void setTargetPosition (double turns) {
        int targetTicks = (int) Math.round(turns * 537.7);
        turretMotor.setTargetPosition(targetTicks);

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
