package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2013.Robot;

/**
 * Turn back/forth from feeder station. Interrupted if either analog stick is
 * moved.
 */
public class FeederTurn extends CommandGroup { 
    public FeederTurn(boolean toward) {
        addSequential(new GyroTurn((toward ? 1 : -1)*110, 0.65));
    }
    
    protected boolean isFinished() {
        return super.isFinished() || Robot.oi.getLeftDrive() != 0 || Robot.oi.getRightDrive() != 0;
    }
}
