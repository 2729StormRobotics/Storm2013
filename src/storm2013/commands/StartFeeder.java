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
public class StartFeeder extends Command {

    boolean goingUp;
    Victor feeder;
    double speed;
    
    public StartFeeder(boolean goingUp){
        this.goingUp = goingUp;
        feeder = new Victor(RobotMap.PORT_MOTOR_FEEDER);
        speed = (goingUp) ? -1 : 1;
    }
    
    protected void initialize() {
        feeder.set(speed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
