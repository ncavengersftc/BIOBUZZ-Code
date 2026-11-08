package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.util.InterpLUT;

public class ShooterInterpLUT {

    private final InterpLUT lut;

    public ShooterInterpLUT() {
        lut = new InterpLUT();

        lut.add(1, 1);
        lut.createLUT();
    }

    public double getVelocity(double distance) {
        return lut.get(distance);
    }

}
