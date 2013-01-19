package storm2013;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    public static final int DRIVE_JOYSTICK_PORT = 1;
    
    public static final int PORT_MOTOR_DRIVE_LEFT         = 8;
    public static final int PORT_MOTOR_DRIVE_RIGHT        = 3;
    
    public static final int PORT_MOTOR_SHOOTER = 5; //# on Donald Duct
    public static final int PORT_HALL_EFFECT = 5; //5 on Donald Duct
}
