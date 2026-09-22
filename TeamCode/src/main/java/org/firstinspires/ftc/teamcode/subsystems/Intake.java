package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private final DcMotor intakeMotor;

    public Intake(HardwareMap hMart) {
        intakeMotor = hMart.get(DcMotor.class, "Intake Motor");
    }

    public void intake(double power){
        intakeMotor.setPower(power);
    }

    public void stop(){
        intakeMotor.setPower(0);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }
}
