package storm2013;

/**
 * The RobotMap is a mapping from where the ports, sensors, and actuators are wired
 * to a variable name. This provides flexibility in changing wiring, makes checking
 * the wiring easier, and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int PORT_JOYSTICK_DRIVE = 1,
                            PORT_JOYSTICK_SHOOT = 2;
    
    public static final int JOYDRIVE_BUTTON_SLOW           = 5;
    public static final int JOYDRIVE_BUTTON_PRINT_ENCODER  = 9;
    public static final int JOYDRIVE_BUTTON_TARGET_2PT     = 1;
    public static final int JOYDRIVE_BUTTON_TARGET_3PT     = 2;
    public static final int JOYSHOOT_BUTTON_SHOOT          = 6;
    public static final int JOYSHOOT_BUTTON_SPIN_DOWN      = 8;
    public static final int JOYSHOOT_BUTTON_RESET_TOMAHAWK = 10;
    
    public static final int JOYDRIVE_AXIS_DRIVE_LEFT  = 2,
                            JOYDRIVE_AXIS_DRIVE_RIGHT = 4,
                            JOYSHOOT_AXIS_TILTER      = 2;
    
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
}
