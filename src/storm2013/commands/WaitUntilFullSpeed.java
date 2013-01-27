/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import storm2013.Robot;

/**
 * Just a {@link DoNothing} command that specifically waits until the firing wheel
 * is at the correct speed.
 * @author evan1026
 */
public class WaitUntilFullSpeed extends DoNothing {

    /**
     * {@inheritDoc}
     */
    protected boolean isFinished() {
        return Robot.shooter.getPIDController().onTarget();
    }
    
}
