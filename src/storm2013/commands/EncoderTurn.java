package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/**
 * Same as {@link EncoderDrive}, but it turns rather than drive. We're not using
 * it this year because encoders suck on skid-steer systems.
 * @author evan1026
 */
public class EncoderTurn extends Command {

    private double _goal;
    private double _dist;
    private double _turnSpeed;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    /**
     * Creates new EncoderTurn {@link Command}.
     * @param goal Double value that we want the encoders to reach.
     * @param turnSpeed Double value that determines the speed of the turn.
     */
    public EncoderTurn(double goal, double turnSpeed){ 
        _goal = goal;
        _turnSpeed = turnSpeed;
        requires(Robot.driveTrain);
    }

    /**
     * Initializes the {@link Command}. All it does is clear the encoders.
     */
    protected void initialize() {
        Robot.driveTrain.clearEncoder();
    }

    /**
     * Executed periodically by the robot. It does the logic of determining what
     * speed to set.
     */
    protected void execute() {
        _dist = Robot.driveTrain.getRightDistance()
                - Robot.driveTrain.getLeftDistance();

        if (_dist < _goal) {
            _driveTrain.tankDrive(-_turnSpeed, _turnSpeed);
        } else if (_dist > _goal) {
            _driveTrain.tankDrive(_turnSpeed, -_turnSpeed);
        }
    }

    /**
     * Tells the robot whether or not the {@link Command} has finished.
     * @return true if we've reached the goal; false otherwise.
     */
    protected boolean isFinished() {
        if(_goal < 0) {
            return _dist <= _goal;
        } else {
            return _dist >= _goal;
        }
    }

    /**
     * Run by robot when the command is done. It stops the drive train.
     */
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    /**
     * Run when the command is unexpectedly stopped. Runs {@link #end()}
     */
    protected void interrupted() {
        end();
    }

    /**
     * 
     * @return the current goal
     */
    public double getGoal() {
        return _goal;
    }

    /**
     * sets the goal
     * @param goal the new goal
     */
    public void setGoal(double goal) {
        _goal = goal;
    }

    /**
     * 
     * @return the current distance
     */
    public double getDist() {
        return _dist;
    }

    /**
     * 
     * @return the current turning speed
     */
    public double getTurnSpeed() {
        return _turnSpeed;
    }

    /**
     * sets the turn speed
     * @param turnSpeed the new turn speed
     */
    public void setTurnSpeed(double turnSpeed) {
        _turnSpeed = turnSpeed;
    }
}
