/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 * Stops the belt that moves the ball to the firing wheel.
 * @author evan1026
 */
public class StopBallFiringMotor extends Command {

    private Victor transferToWheelMotor;
    
    /**
     * Constructor and such.
     */
    public StopBallFiringMotor(){
        transferToWheelMotor = new Victor(RobotMap.PORT_MOTOR_BALL_FIRE_BELT);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
        transferToWheelMotor.set(0);
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
