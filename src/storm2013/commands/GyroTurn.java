package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/** Turns the robot until a desired heading is reached. */
public class GyroTurn extends Command {

    private double _goal;
    private double _dist;
    private double _turnSpeed;
    
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    /**
     * @param goal      Desired heading
     * @param turnSpeed Speed at which to turn
     */
    public GyroTurn(double goal, double turnSpeed){
        _goal = goal;
        _turnSpeed = turnSpeed;
        requires(Robot.driveTrain);
    }
    protected void initialize() {
        Robot.driveTrain.clearGyro();
    }
    protected void execute() {
        _dist = Robot.driveTrain.getGyroAngle();
        
        if (_goal < 0){
            _driveTrain.tankDrive(-_turnSpeed, _turnSpeed);
        }
        else{
            _driveTrain.tankDrive(_turnSpeed, -_turnSpeed);
        }
    }
    protected boolean isFinished() {
        if(_goal < 0) {
            return _dist <= _goal;
        }
        else {
            return _dist >= _goal;
        }
    }
    protected void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }
    protected void interrupted() {
        end();
    }
}
