package storm2013;

import edu.wpi.first.wpilibj.Joystick;

/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {    
    private Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE);
    
    public OI() {
        
        

       
    }
    
    // When a joystick is in its zero position, it will not necessarily read
    // zero. This makes sure that everything within that range reads zero.
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
    }
    
    public double getLeftDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_LEFT),0.15);
    }
    
    public double getRightDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_RIGHT),0.15);
    }
}

