/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 *
 * @author evan1026
 */
public class ControlLoader extends Command {

    Victor ballLiftMotor;
    double speed;
    
    public ControlLoader(boolean goingUp){
        ballLiftMotor = new Victor(RobotMap.PORT_MOTOR_BALL_LIFT);
        speed = (goingUp) ? -1 : 1;
    }
    
    protected void initialize() {
        ballLiftMotor.set(speed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        ballLiftMotor.set(0);
    }

    protected void interrupted() {
        end();
    }
    
}
