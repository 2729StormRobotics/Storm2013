package storm2013.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.commands.LEDcommands.SetModeSpinningUp;

/**
 * Spins up the firing wheel.
 * @author evan1026
 */
public class SpinUp extends Command {
    
    private Timer _onTargetTimer = new Timer();
    private final Command lightCommand = new SetModeSpinningUp();

    /**
     * Creates a new instance blahdy blahdy blah.
     */
    public SpinUp(){
        requires(Robot.shooter);
    }
    
    /**
     * Initializes {@link Command}
     */
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(3400);
        Robot.shooter.getPIDController().enable();
        _onTargetTimer.start();
        _onTargetTimer.reset();
        lightCommand.start();
    }

    /**
     * Executes over and over and such
     */
    protected void execute() {
        if(!Robot.shooter.onTarget()) {
            _onTargetTimer.reset();
        }
    }

    /**
     * Returns true when the shooter is on target
     */
    protected boolean isFinished() {
        return _onTargetTimer.get() > 0.3;
    }

    /**
     * Called when {@link Command} is done
     */
    protected void end() {
        lightCommand.cancel();
    }

    /**
     * Called when {@link Command} is interrupted
     */
    protected void interrupted() {
        end();
    }
}
