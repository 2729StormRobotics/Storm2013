/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 * Starts the motor for the thingy (so technical) that moves the ball to the firing wheel. 
 * @author evan1026
 */
public class StartBallFiringMotor extends Command {

    private Victor transferToWheelMotor;
    private DigitalInput ballReadySensor;
    
    /**
     * Makes a new one of these.
     */
    public StartBallFiringMotor(){
        transferToWheelMotor = new Victor(RobotMap.PORT_MOTOR_BALL_FIRE_BELT);
        ballReadySensor = new DigitalInput(RobotMap.PORT_BREAKBEAM_BALL_READY);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
        transferToWheelMotor.set(-1);
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
        return ballReadySensor.get();
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
