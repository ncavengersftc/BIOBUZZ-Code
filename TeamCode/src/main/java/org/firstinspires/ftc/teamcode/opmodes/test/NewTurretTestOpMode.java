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
        turret.setTargetAngle(60);

        telemetry.addData("Status", "Dashboard Initialized");
        telemetry.update();

        register(turret);
    }


    @Override
    public void run(){
        super.run();
        telemetry.addData("Turret Angle", turret.getCurrentDistance());
        telemetry.addData("Turret at Target",turret.atTarget());
        telemetry.addData("Turret Ticks", turret.getCurrentTicks());
        telemetry.update();
    }



}
