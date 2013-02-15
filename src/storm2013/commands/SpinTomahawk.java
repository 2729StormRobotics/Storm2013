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
public class SpinTomahawk extends Command {
    
    
    private boolean _hasChanged;
    
    public SpinTomahawk(){
        requires(Robot.tomahawk);
    }
    
    protected void initialize() {
        Robot.tomahawk.move();
        _hasChanged = false;
        System.out.println("Turning tomahawk");
    }

    protected void execute() {
        if (Robot.tomahawk.isForward()) {
            System.out.println("tomahawk forward");
            _hasChanged = true;
        }
    }

    protected boolean isFinished() {
        return _hasChanged && !Robot.tomahawk.isForward();
    }

    protected void end() {
        Robot.tomahawk.stop();
    }

    protected void interrupted() {
        end();
    }

    public synchronized boolean isInterruptible() {
        return false;
    }
}
