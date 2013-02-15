package storm2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2013.RobotMap;

/**
 * Provides functionality for interfacing with the tomahawk
 * @author Joe
 */
public class Tomahawk extends Subsystem {
    private static double MOTOR_SPEED = 1;
    
    private Victor _motor;
    private DigitalInput _limitSwitch;
    
    /**
     * Instantiates a new one of these subsystem things and allocates the ports
     * it needs
     */
    public Tomahawk() {
        _motor = new Victor(RobotMap.PORT_MOTOR_TOMAHAWK);
        _limitSwitch = new DigitalInput(RobotMap.PORT_LIMIT_TOMAHAWK);
    }

    /**
     * Doesn't do anything here, but the superclass wants it.
     */
    public void initDefaultCommand() {}
    
    /**
     * Returns whether or not the tomahawk limit switch is forward (meaning the
     * tomahawk is no longer pushing it).
     * @return true if forward, false otherwise
     */
    public boolean isForward() {
        return _limitSwitch.get();
    }
    
    /**
     * Spins the tomahawk's window motor.
     */
    public void move() {
//        if(Robot.shooter.isEnabled()) {
            _motor.set(MOTOR_SPEED);
//        }
    }
    
    /**
     * Stops the tomahawk
     */
    public void stop() {
        _motor.set(0);
    }
}
