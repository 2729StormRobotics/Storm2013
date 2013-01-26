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
 * 
 *
 * @author evan1026
 */
public class StartBallFiringMotor extends Command {

    Victor transferToWheelMotor;
    DigitalInput ballReadySensor;
    boolean done;
    
    public StartBallFiringMotor(){
        transferToWheelMotor = new Victor(RobotMap.PORT_MOTOR_BALL_FIRE_BELT);
        ballReadySensor = new DigitalInput(RobotMap.PORT_BREAKBEAM_BALL_READY);
    }
    
    protected void initialize() {
        transferToWheelMotor.set(-1);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return ballReadySensor.get();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
