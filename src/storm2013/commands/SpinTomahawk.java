package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.commands.LEDcommands.SetModeFiring;

/**
 * Spins the tomahawk 1 revolution by using a limit switch.
 * @author evan1026
 */
public class SpinTomahawk extends Command {
    
    private final Command lightCommand = new SetModeFiring();
    private final boolean _forward;
    private boolean _hasChanged;
    
    /**
     * Constructor.
     */
    public SpinTomahawk(boolean forward){
        requires(Robot.tomahawk);
        _forward = forward;
    }
    
    /**
     * Initializes {@link Command}
     */
    protected void initialize() {
        Robot.tomahawk.move(_forward);
        _hasChanged = false;
        lightCommand.start();
    }

    /**
     * Does some logic to get the command to stop at the right time
     */
    protected void execute() {
        if (Robot.tomahawk.isForward()) {
            _hasChanged = true;
        }
    }

    /**
     * Returns true when the limit switch has been pressed again after being released
     * @return whether or not it's done
     */
    protected boolean isFinished() {
        return _hasChanged && !Robot.tomahawk.isForward();
    }

    /**
     * Stops the tomahawk when the {@link Command} is done
     */
    protected void end() {
        Robot.tomahawk.stop();
        lightCommand.cancel();
    }

    /**
     * Runs {@link #end()}
     */
    protected void interrupted() {
        end();
    }
}
