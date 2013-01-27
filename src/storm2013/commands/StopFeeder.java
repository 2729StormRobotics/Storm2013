/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 * Stops the feeder.
 * @author evan1026
 */
public class StopFeeder extends Command {

    private Victor feeder;
    
    /**
     * Come on, you know what this does.
     */
    public StopFeeder(){
        feeder = new Victor(RobotMap.PORT_MOTOR_FEEDER);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
        feeder.set(0);
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
