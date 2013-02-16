package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Spins down the firing wheel.
 * @author evan1026
 */
public class SpinDown extends Command {

    /**
     * Creates a new instance of the command.
     */
    public SpinDown(){
        requires(Robot.shooter);
    }
    
    /**
     * Initializes the {@link Command}
     */
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(0);
    }

    /**
     * Does nothing
     */
    protected void execute() {
    }

    /**
     * Tells if the {@link Command} is done
     * @return If the shooter is at its target.
     */
    protected boolean isFinished() {
        return Robot.shooter.onTarget();
    }

    /**
     * Ends the {@link Command}
     */
    protected void end() {
    }

    /**
     * Also ends the {@link Command}
     */
    protected void interrupted() {
        end();
    }
}
