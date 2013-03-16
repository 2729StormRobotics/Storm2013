package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/** Moves the tilter downward. */
public class LowerTilter extends Command {
    public LowerTilter() {
        requires(Robot.tilter);
    }
    
    protected void initialize() {
        Robot.tilter.moveDown();
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
