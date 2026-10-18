package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Transfer extends SubsystemBase {
    private final MotorEx transferMotor;

    public Transfer(HardwareMap hardwareMap) {
        transferMotor = new MotorEx(hardwareMap, "Transfer Motor", Motor.GoBILDA.RPM_312);
    }

    public void turn(double power) {
        transferMotor.set(power);

    }

    public void stop() {
        transferMotor.set(0);
    }

}