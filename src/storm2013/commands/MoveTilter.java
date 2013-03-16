package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.OI;
import storm2013.Robot;
import storm2013.subsystems.Tilter;

/**
 * Moves the tilter based on user input as configured in {@link OI}
 * @author Joe
 */
public class MoveTilter extends Command {
    public MoveTilter() {
        requires(Robot.tilter);
    }
    
    protected void initialize() {}
    protected void execute() {
        Robot.tilter.move(Robot.oi.getTilterAxis()*Tilter.SPEED_DEFAULT);
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {}
    protected void interrupted() {
        end();
    }
}
