package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.commands.LEDcommands.SetModeFiring;
import storm2013.subsystems.Tomahawk;

/**
 * Spins the tomahawk 1 revolution; sets the LED color as it does. To spin
 * exactly one revolution, it runs until it meets the falling edge of
 * tomahawk.isForward().
 */
public class SpinTomahawk extends Command {
    private final Command lightCommand = new SetModeFiring();
    private final boolean _forward;
    private boolean _hasChanged;
    
    public SpinTomahawk(boolean forward){
        requires(Robot.tomahawk);
        _forward = forward;
    }
    
    protected void initialize() {
        Robot.tomahawk.move(_forward);
        _hasChanged = false;
        lightCommand.start();
    }
    
    protected void execute() {
        if (Robot.tomahawk.isForward()) {
            _hasChanged = true;
        }
    }
    protected boolean isFinished() {
        return _hasChanged && !Robot.tomahawk.isForward();
    }
    protected void end() {
        Robot.tomahawk.stop();
        lightCommand.cancel();
    }
    protected void interrupted() {
        end();
    }
}
