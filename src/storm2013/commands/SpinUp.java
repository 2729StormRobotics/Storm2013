package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Spins up the firing wheel.
 * @author evan1026
 */
public class SpinUp extends Command {

    /**
     * Creates a new instance blahdy blahdy blah.
     */
    public SpinUp(){
        requires(Robot.shooter);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(2500);
        Robot.shooter.getPIDController().enable();
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
        return Robot.shooter.getPIDController().onTarget();
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
