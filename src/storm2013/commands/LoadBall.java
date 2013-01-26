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
 * @author evan1026
 */
public class LoadBall extends Command {

    DigitalInput ballLoadedSensor;
    ControlLoader controlLoader;
    
    public LoadBall(){
        ballLoadedSensor = new DigitalInput(RobotMap.PORT_BREAKBEAM_BALL_READY);
        controlLoader = new ControlLoader(true);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !ballLoadedSensor.get();
    }

    protected void end() {
        controlLoader.end();
    }

    protected void interrupted() {
        end();
    }
    
}
