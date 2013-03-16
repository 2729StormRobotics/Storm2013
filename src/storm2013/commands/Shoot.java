package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Spins up the shooter, then spins the tomahawk once when it is up to speed. If
 * it is interrupted partway through spinning the tomahawk, it will spawn
 * another {@link SpinTomahawk} to finish the rotation.
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
    
    public Shoot(double speed) {
        addSequential(new _EndTomahawk());
        addSequential(new SpinUp(speed));
        addSequential(new _BeginTomahawk());
        addSequential(new SpinTomahawk(true));
        addSequential(new _EndTomahawk());
    }
    
    public Shoot() {
        this(SpinUp.SPEED_NORMAL);
    }

    protected void interrupted() {
        if(_spinningTomahawk) {
            new SpinTomahawk(true).start();
        }
    }
}
