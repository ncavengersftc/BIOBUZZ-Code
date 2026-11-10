package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class Turret extends SubsystemBase {

    private final MotorEx turretMotor;

    private static final double TICKS_PER_TURN = 537.7;
    private static final double MIN_TURNS = -0.46;
    private static final double MAX_TURNS = 0.46;
    private static final double KP = 0.8;
    private static final double MAX_POWER = 0.25;

    private double setpoint = 0;

    public Turret(HardwareMap hardwareMap) {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.setRunMode(Motor.RunMode.RawPower);
    }

    public void trackTarget(double tx) {
        if (!Double.isFinite(tx)) {
            stop();
            return;
        }

        if (Math.abs(tx) < 1.0) {
            stop();
            return;
        }

        setTurns(setpoint + tx * 0.001);
    }

    public void setTurns(double turns) {
        setpoint = Math.max(MIN_TURNS, Math.min(MAX_TURNS, turns));
        goToTurns(setpoint);
    }

    public void goToTurns(double targetTurns) {
        double currentTurns = getTurns();
        double error = targetTurns - currentTurns;

        if (Math.abs(error) < 0.01) {
            stop();
            return;
        }

        if (currentTurns >= MAX_TURNS && error > 0) {
            setpoint = MIN_TURNS;
        } else if (currentTurns <= MIN_TURNS && error < 0) {
            setpoint = MAX_TURNS;
        }

        error = setpoint - currentTurns;
        double power = Math.max(-MAX_POWER, Math.min(MAX_POWER, error * KP));

        turretMotor.set(power);
    }

    public double getTurns() {
        return turretMotor.getCurrentPosition() / TICKS_PER_TURN;
    }

    public void stop() {
        turretMotor.stopMotor();
    }
}