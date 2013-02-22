package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Spins up the shooter and spins the tomahawk.
 * @author Joe
 */
public class Shoot extends CommandGroup {
    
    private boolean _spinningTomahawk = false;
    
    private class _BeginTomahawk extends Command {
        protected void initialize() {
            _spinningTomahawk = true;
        }
        protected void execute() {}
        protected boolean isFinished() {
            return true;
        }
        protected void end() {}
        protected void interrupted() {}
    };
    private class _EndTomahawk extends Command {
        protected void initialize() {
            _spinningTomahawk = false;
        }
        protected void execute() {}
        protected boolean isFinished() {
            return true;
        }
        protected void end() {}
        protected void interrupted() {}
    };
    
    /**
     * Constructor stuff.
     */
    public Shoot() {
        addSequential(new _EndTomahawk());
        addSequential(new SpinUp());
        addSequential(new _BeginTomahawk());
        addSequential(new SpinTomahawk(true));
        addSequential(new _EndTomahawk());
    }

    protected void interrupted() {
        if(_spinningTomahawk) {
            new SpinTomahawk(true).start();
        }
    }
}
