package storm2013;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int PORT_JOYSTICK_DRIVE      = 1;
    public static final int BUTTON_PRINT_ENCODER     = 9;
    public static final int BUTTON_TILTER_UP         = 6;
    public static final int BUTTON_TILTER_DOWN       = 4;
    public static final int BUTTON_SHOOT             = 1;
    
    public static final int PORT_MOTOR_DRIVE_LEFT    = 1;
    public static final int PORT_MOTOR_DRIVE_RIGHT   = 10;
    
    public static final int PORT_MOTOR_SHOOTER       = 3;
    public static final int PORT_MOTOR_TILTER        = 2;
    public static final int PORT_MOTOR_TOMAHAWK      = 4;
    
    public static final int PORT_SENSOR_HALL_EFFECT  = 5;
//    public static final int PORT_SENSOR_VOLTAGE_LOAD = 2;
    public static final int PORT_SENSOR_GYRO         = 2;
    
    public static final int PORT_ENCODER_LEFT_1      = 3;
    public static final int PORT_ENCODER_LEFT_2      = 4;
    public static final int PORT_ENCODER_RIGHT_1     = 2;
    public static final int PORT_ENCODER_RIGHT_2     = 1;
    
    
    public static final int PORT_LIMIT_TOMAHAWK      = 6;
}
