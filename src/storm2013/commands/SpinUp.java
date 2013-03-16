package storm2013.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.commands.LEDcommands.SetModeSpinningUp;

/**
 * Spins up the firing wheel, and waits for it to be on target. The LEDs are
 * flashed quicker as it gets closer to its target speed. At the beginning, it
 * runs the shooter full-throttle until SPEED_PID_MIN is reached, then sets the
 * motor value to MOTORVAL_PID_MIN, then uses PID control the rest of the way.
 */
public class SpinUp extends Command {
    public static final double SPEED_NORMAL    = 3400,
                               SPEED_FULLCOURT = 4000; // tentative
    private static final double SPEED_PID_MIN    = 2500,
                                MOTORVAL_PID_MIN = -0.6; // TODO: configure this to match
    
    private final Timer _onTargetTimer = new Timer();
    private final Command lightCommand = new SetModeSpinningUp();
    private final double _speed;

    public SpinUp(double speed){
        requires(Robot.shooter);
        _speed = speed;
    }
    public SpinUp() {
        this(SPEED_NORMAL);
    }
    
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(_speed);
        Robot.shooter.enable();
        _onTargetTimer.start();
        _onTargetTimer.reset();
        lightCommand.start();
    }
    protected void execute() {
        if(!Robot.shooter.onTarget()) {
            _onTargetTimer.reset();
        }
        if(Robot.shooter.getSpeedRpm() < SPEED_PID_MIN) {
            Robot.shooter.disable();
            Robot.shooter.setMotorValRaw(-1);
        } else if(!Robot.shooter.isEnabled()) {
            Robot.shooter.setMotorValRaw(MOTORVAL_PID_MIN);
            Robot.shooter.enable();
        }
    }
    protected boolean isFinished() {
        return _onTargetTimer.get() > 0.3;
    }
    protected void end() {
        lightCommand.cancel();
    }
    protected void interrupted() {
        end();
    }
}
