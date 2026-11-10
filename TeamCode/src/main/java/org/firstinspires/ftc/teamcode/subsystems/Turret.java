
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
    private static final double TOLERANCE = 0.01;

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

        double desiredTurns = setpoint + (tx * 0.001);
        setTurns(desiredTurns);
    }

    public void setTurns(double turns) {
        setpoint = Math.max(MIN_TURNS, Math.min(MAX_TURNS, turns));
        goToTurns(setpoint);
    }

    public void goToTurns(double targetTurns) {
        double currentTurns = getTurns();

        double clockwiseDistance = targetTurns - currentTurns;
        double counterclockwiseDistance = currentTurns - targetTurns;

        boolean clockwiseSafe = targetTurns > currentTurns && targetTurns <= MAX_TURNS;
        boolean counterclockwiseSafe = targetTurns < currentTurns && targetTurns >= MIN_TURNS;

        if (Math.abs(targetTurns - currentTurns) <= TOLERANCE) {
            stop();
            return;
        }

        if (clockwiseSafe) {
            moveTo(targetTurns, currentTurns);
        } else if (counterclockwiseSafe) {
            moveTo(targetTurns, currentTurns);
        } else {
            stop();
        }
    }

    private void moveTo(double targetTurns, double currentTurns) {
        double error = targetTurns - currentTurns;
        double power = error * KP;

        power = Math.max(-MAX_POWER, Math.min(MAX_POWER, power));

        turretMotor.set(power);
    }

    public double getTurns() {
        return turretMotor.getCurrentPosition() / TICKS_PER_TURN;
    }

    public void stop() {
        turretMotor.stopMotor();
    }
}