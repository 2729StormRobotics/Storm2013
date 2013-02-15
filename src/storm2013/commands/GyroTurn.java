package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/**
 * Turns the robot based on the gyro. This is preferable because of the skid-steer
 * system.
 * @author evan1026
 */
public class GyroTurn extends Command {

    private double _goal;
    private double _dist;
    private double _turnSpeed;
    
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    /**
     * Creates a new instance of GyroTurn.
     * @param goal The goal to which we want to turn.
     * @param turnSpeed The speed at which we want to turn.
     */
    public GyroTurn(double goal, double turnSpeed){
        _goal = goal;
        _turnSpeed = turnSpeed;
        requires(Robot.driveTrain);
    }
    
    /**
     * Initializes the {@link Command}
     */
    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }

    /**
     * Is executed periodically by the robot.
     */
    protected void execute() {
        _dist = Robot.driveTrain.getGyroAngle();
        
        if (_goal < 0){
            _driveTrain.tankDrive(-_turnSpeed, _turnSpeed);
        }
        else{
            _driveTrain.tankDrive(_turnSpeed, -_turnSpeed);
        }
    }

    /**
     * Tells the robot if the {@link Command} is finished.
     * @return True if done; false otherwise.
     */
    protected boolean isFinished() {
        if(_goal < 0) {
            return _dist <= _goal;
        }
        else {
            return _dist >= _goal;
        }
    }

    /**
     * Stops the robot when the {@link Command} is over.
     */
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }

    /**
     * Runs {@link #end()} when the {@link Command} needs to be unexpectedly stopped.
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
