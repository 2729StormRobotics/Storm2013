/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author evan1026
 */
public class ChangeLEDMode extends Command {
    protected void initialize() {
        Robot.leds.nextMode();
    }
    protected void execute() {}
    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
    protected void interrupted() {}
}
