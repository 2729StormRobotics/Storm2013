package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author Joe
 */
public class RaiseTilter extends Command {
    
    public RaiseTilter() {
        requires(Robot.tilter);
    }

    protected void initialize() {
        System.out.println("Tilting");
        Robot.tilter.moveUp();
    }

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.tilter.stop();
    }

    protected void interrupted() {
        end();
    }
}
