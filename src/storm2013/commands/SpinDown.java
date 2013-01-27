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
     * {@inheritDoc}
     */
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(0);
    }

    /**
     * {@inheritDoc}
     */
    protected void execute() {
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isFinished() {
        return Robot.shooter.onTarget();
    }

    /**
     * {@inheritDoc}
     */
    protected void end() {
    }

    /**
     * {@inheritDoc}
     */
    protected void interrupted() {
        end();
    }
}
