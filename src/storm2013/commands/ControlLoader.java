package storm2013.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 * Controls the loading thing in the back that pulls the balls up to the shooter.
 * @author evan1026
 */
public class ControlLoader extends Command {

    private Victor ballLiftMotor;
    private double speed;
    
    /**
     * Controls the loading thing in the back that pulls the balls up to the shooter.
     * @param goingUp True to move the balls up or false to move them down.
     */
    public ControlLoader(boolean goingUp){
        ballLiftMotor = new Victor(RobotMap.PORT_MOTOR_BALL_LIFT);
        speed = (goingUp) ? -1 : 1;
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
        ballLiftMotor.set(speed);
    }

    /**
     * {@inheritDoc}
     */
    protected void execute() {
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    protected void end() {
        ballLiftMotor.set(0);
    }

    /**
     * {@inheritDoc}
     */
    protected void interrupted() {
        end();
    }
}
