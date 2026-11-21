package org.firstinspires.ftc.teamcode.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.NewTurret;

@TeleOp(name = "New Turret Test", group = "Test")
public class NewTurretTestOpMode extends CommandOpMode {
    private NewTurret turret;

    @Override
    public void initialize(){
        turret = new NewTurret(hardwareMap);
        turret.setTargetTicks(2);
    }

    @Override
    public void run(){
        super.run();
    }



}
