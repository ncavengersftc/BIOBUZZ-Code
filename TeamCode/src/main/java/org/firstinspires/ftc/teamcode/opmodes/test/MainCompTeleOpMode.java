package org.firstinspires.ftc.teamcode.opmodes.test;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class MainCompTeleOpMode extends CommandOpMode {
    private GamepadEx driver;

    @Override public void initialize(){
        driver = new GamepadEx(gamepad1);
    }
}
