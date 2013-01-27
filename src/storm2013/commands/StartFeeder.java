/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 * Starts up the feeder motor.
 * @author evan1026
 */
public class StartFeeder extends Command {

    private Victor feeder;
    private double speed;
    
    /**
     * Makes a new one of these commands.
     * @param goingUp True if feeding in, false if pushing out.
     */
    public StartFeeder(boolean goingUp){
        feeder = new Victor(RobotMap.PORT_MOTOR_FEEDER);
        speed = (goingUp) ? -1 : 1;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
        feeder.set(speed);
    }

    /**
     * {@inheritDoc}
     */
    protected void execute() {
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    protected void end() {
    }

    /**
     * {@inheritDoc}
     */
    protected void interrupted() {
        end();
    }
}
