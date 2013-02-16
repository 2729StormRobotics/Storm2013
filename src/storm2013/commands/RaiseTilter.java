package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Raises the tilter
 * @author Joe
 */
public class RaiseTilter extends Command {
    
    /**
     * Constructor.
     */
    public RaiseTilter() {
        requires(Robot.tilter);
    }

    /**
     * Initializes the {@link Command}.
     */
    protected void initialize() {
        System.out.println("Tilting");
        Robot.tilter.moveUp();
    }

    /**
     * Does nothing
     */
    protected void execute() {}

    /**
     * Returns false to make it run while a button is pressed.
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Stops the tilter.
     */
    protected void end() {
        Robot.tilter.stop();
    }

    /**
     * Calls {@link #end()}
     */
    protected void interrupted() {
        end();
    }
}
