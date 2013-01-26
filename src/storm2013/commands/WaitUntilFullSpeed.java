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
public class WaitUntilFullSpeed extends DoNothing {

    protected boolean isFinished() {
        return Robot.shooter.getPIDController().onTarget();
    }
    
}
