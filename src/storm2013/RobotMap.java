package storm2013;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int PORT_JOYSTICK_DRIVE = 1;
    
    public static final int PORT_MOTOR_DRIVE_LEFT         = 8;
    public static final int PORT_MOTOR_DRIVE_RIGHT        = 3;
    
    public static final int PORT_MOTOR_SHOOTER = 5; //# on Donald Duct
    public static final int PORT_HALL_EFFECT = 5; //5 on Donald Duct
    public static final int PORT_VOLTAGE_LOAD = 2;
}
