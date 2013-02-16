package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Does exactly what its name suggests
 * @author Storm
 */
public class DoNothing extends Command {
    
    /**
     * Default constructor.
     */
    public DoNothing() {}

    /**
     * Initializes the {@link Command}. As the name suggests, it does
     * nothing.
     */
    protected void initialize() {}

    /**
     * Executes the {@link Command} periodically. As the name suggests, it does
     * nothing.
     */
    protected void execute() {}

    /**
     * Returns whether or not the command has finished. Returns false to allow the
     * command to run continually until it times out.
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Run when the command is over. As the name suggests, it does
     * nothing.
     */
    protected void end() {}

    /**
     * Run when the command needs to be stopped unexpectedly. As the name suggests, 
     * it does nothing.
     */
    protected void interrupted() {}
}
