package storm2013;

/**
 * Holds all of our hardware/joystick mappings, so they're pretty easy to configure.
 */
public class RobotMap {
    public static final int PORT_JOYSTICK_DRIVE = 1,
                            PORT_JOYSTICK_SHOOT = 2,
                            PORT_JOYSTICK_DEBUG = 3;
    
    public static final int JOYDRIVE_BUTTON_SLOW            = 5;
    public static final int JOYDRIVE_BUTTON_RAINBOW         = 6;
    public static final int JOYDRIVE_BUTTON_SPAZ            = 8;
    public static final int JOYDRIVE_BUTTON_PRINT_ENCODER   = 9;
    public static final int JOYDRIVE_BUTTON_TARGET_2PT      = 1;
    public static final int JOYDRIVE_BUTTON_TARGET_3PT      = 2;
    
    public static final int JOYSHOOT_BUTTON_SHOOT           = 6;
    public static final int JOYSHOOT_BUTTON_SHOOT_FULLCOURT = 5;
    public static final int JOYSHOOT_BUTTON_SPIN_DOWN       = 8;
    public static final int JOYSHOOT_BUTTON_SPIN_DOWN2      = 7;
    public static final int JOYSHOOT_BUTTON_TARGET_2PT      = 1;
    public static final int JOYSHOOT_BUTTON_TARGET_3PT      = 2;
    public static final int JOYSHOOT_BUTTON_TOMAHAWK_BACK   = 9;
    public static final int JOYSHOOT_BUTTON_TOMAHAWK        = 10;
    public static final int JOYSHOOT_BUTTON_TILTER_SAFETY_1 = 5;
    public static final int JOYSHOOT_BUTTON_TILTER_SAFETY_2 = 7;
    public static final int JOYSHOOT_BUTTON_TILTTODIST      = 3;
    public static final int JOYSHOOT_BUTTON_LOGPOT          = 4;
    
    public static final int JOYDEBUG_BUTTON_CONTROLSHOOT = 10;
    
    public static final int JOYDRIVE_AXIS_DRIVE_LEFT  = 2,
                            JOYDRIVE_AXIS_DRIVE_RIGHT = 4,
                            JOYDRIVE_AXIS_FEEDERTURN  = 5,
                            JOYSHOOT_AXIS_TILTER      = 2,
                            JOYSHOOT_AXIS_DISTANCE    = 6,
                            JOYDEBUG_AXIS_SHOOTSPEED  = 4;
    
    public static final int PORT_MOTOR_DRIVE_LEFT     = 1;
    public static final int PORT_MOTOR_DRIVE_RIGHT    = 10;
    
    public static final int PORT_MOTOR_SHOOTER        = 3;
    public static final int PORT_MOTOR_TILTER         = 2;
    public static final int PORT_MOTOR_TOMAHAWK       = 4;
    
    public static final int PORT_SENSOR_HALL_EFFECT   = 5;
//    public static final int PORT_SENSOR_VOLTAGE_LOAD = 2;
    public static final int PORT_SENSOR_GYRO          = 1;
    
    public static final int MODULE_SENSOR_ACCELEROMETER = 1;
    
    public static final int PORT_ENCODER_LEFT_1       = 3;
    public static final int PORT_ENCODER_LEFT_2       = 4;
    public static final int PORT_ENCODER_RIGHT_1      = 2;
    public static final int PORT_ENCODER_RIGHT_2      = 1;
    
    
    public static final int PORT_LIMIT_TOMAHAWK       = 6;
    public static final int PORT_LIMIT_TILTER_TOP     = 7;
    
    public static final int PORT_STRINGPOT            = 7;
}
