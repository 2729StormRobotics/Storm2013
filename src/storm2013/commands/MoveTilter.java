package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.OI;
import storm2013.Robot;

/**
 * Moves the tilter based on user input as configured in {@link OI}
 * @author Joe
 */
public class MoveTilter extends Command {
    
    /**
     * The constructor.
     */
    public MoveTilter() {
        requires(Robot.tilter);
    }

    /**
     * Initializes command. Doesn't actually do anything.
     */
    protected void initialize() {}

    /**
     * Executed periodically. It reads input values and applies them to the tilter.
     */
    protected void execute() {
        Robot.tilter.move(Robot.oi.getTilterAxis()*Robot.tilter.SPEED_DEFAULT);
    }

    /**
     * This command is to be used continuously so this just returns false, indicating
     * that it is never finished.
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called when the command is ending. Doesn't do anything in this case.
     */
    protected void end() {}

    /**
     * Called when the command is interrupted. Calls {@link #end()}
     */
    protected void interrupted() {
        end();
    }
}
