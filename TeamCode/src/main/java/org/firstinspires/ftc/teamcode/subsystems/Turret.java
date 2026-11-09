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

    private double scanPower = 0.1;

    public Turret(HardwareMap hardwareMap) {
        turretMotor = new MotorEx(hardwareMap, "Turret Motor", Motor.GoBILDA.RPM_312);
        turretMotor.setRunMode(Motor.RunMode.RawPower);
    }

    public void trackTarget(double tx) {
        double currentTurns = getTurns();

        // No tag detected: scan back and forth
        if (!Double.isFinite(tx)) {
            if (currentTurns >= MAX_TURNS) scanPower = -0.1;
            if (currentTurns <= MIN_TURNS) scanPower = 0.1;
            turretMotor.set(scanPower);
            return;
        }

        // Stop when tag is centered
        if (Math.abs(tx) < 1.0) {
            stop();
            return;
        }

        // Calculate tracking power
        double power = tx * 0.015;
        power = Math.max(-0.25, Math.min(0.25, power));

        // Protect rotation limits
        if (currentTurns >= MAX_TURNS && power > 0) power = 0;
        if (currentTurns <= MIN_TURNS && power < 0) power = 0;

        turretMotor.set(power);
    }

    public double getTurns() {
        return turretMotor.getCurrentPosition() / TICKS_PER_TURN;
    }

    public void stop() {
        turretMotor.stopMotor();

    }
}