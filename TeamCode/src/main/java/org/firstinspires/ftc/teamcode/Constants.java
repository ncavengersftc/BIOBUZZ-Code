package org.firstinspires.ftc.teamcode;

public class Constants {

    //********************************************TURRET********************************************
    public static final double TURRET_GEAR_RATIO = 2.7;
    public static final int TURRET_MIN_TICKS = -10;
    public static final int TURRET_MAX_TICKS = 2;

    // Convert ticks to degrees based on GoBILDA 312 RPM motor (537.7 CPR) and gear ratio
    public static final double TURRET_TICKS_PER_DEGREE = (537.7 * TURRET_GEAR_RATIO) / 360.0;
    public static final double TURRET_MIN_DEG = TURRET_MIN_TICKS / TURRET_TICKS_PER_DEGREE;
    public static final double TURRET_MAX_DEG = TURRET_MAX_TICKS / TURRET_TICKS_PER_DEGREE;

    public static final double TURRET_KP = 0.01;
    public static final double TURRET_TOLERANCE_DEG = 2.0;
    public static final double TURRET_VISION_TOLERANCE_DEG = 2.0;
    public static final double TURRET_MAX_POWER = 0.6;

}
