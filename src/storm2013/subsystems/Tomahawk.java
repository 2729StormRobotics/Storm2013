package storm2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2013.RobotMap;

/** Subsystem including the Tomahawk and its IR sensor. */
public class Tomahawk extends Subsystem {
    private static double MOTOR_SPEED = 1;
    
    private Victor _motor;
    private DigitalInput _limitSwitch;
    
    public Tomahawk() {
        _motor = new Victor(RobotMap.PORT_MOTOR_TOMAHAWK);
        _limitSwitch = new DigitalInput(RobotMap.PORT_LIMIT_TOMAHAWK);
    }

    protected void initDefaultCommand() {}
    
    /** Reads whether the tomahawk is forward (not triggering the IR sensor). */
    public boolean isForward() {
        return _limitSwitch.get();
    }
    
    /** Moves the tomahawk (true = normal direction,false = reverse). */
    public void move(boolean forward) {
        _motor.set((forward ? 1 : -1)*MOTOR_SPEED);
    }
    
    /** Stops the tomahawk. */
    public void stop() {
        _motor.set(0);
    }
}
