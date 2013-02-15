package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Lowers the tilter.
 * @author Joe
 */
public class LowerTilter extends Command {
    
    /**
     * Default constructor.
     */
    public LowerTilter() {
        requires(Robot.tilter);
    }

    /**
     * Initializes the {@link Command}
     */
    protected void initialize() {
        Robot.tilter.moveDown();
    }

    /**
     * Does nothing.
     */
    protected void execute() {}

    /**
     * Tells if {@link Command} is done.
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Ends the {@link Command}
     */
    protected void end() {
        Robot.tilter.stop();
    }

    /**
     * Also ends the {@link Command}
     */
    protected void interrupted() {
        end();
    }
}
