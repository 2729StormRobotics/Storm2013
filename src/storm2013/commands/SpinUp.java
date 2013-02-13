package storm2013.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Spins up the firing wheel.
 * @author evan1026
 */
public class SpinUp extends Command {
    private Timer _onTargetTimer = new Timer();

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
        Robot.shooter.getPIDController().setSetpoint(3400);
        Robot.shooter.getPIDController().enable();
        _onTargetTimer.start();
        _onTargetTimer.reset();
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
