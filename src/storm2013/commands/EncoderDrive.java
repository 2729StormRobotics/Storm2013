package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/** Drives straight forward/back until a desired encoder value is reached. */
public class EncoderDrive extends Command {
    private double _goal;
    private double _dist;
    private double _driveSpeed;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    /**
     * @param goal       Target encoder value
     * @param driveSpeed Signal sent to the motors
     */
    public EncoderDrive(double goal, double driveSpeed){ 
        
        _goal = goal;
        _driveSpeed = driveSpeed;
        requires(Robot.driveTrain);
    }
    
    protected void initialize() {
       Robot.driveTrain.clearEncoder();
    }
    protected void execute() {
        double left  = Robot.driveTrain.getLeftDistance(),
               right = Robot.driveTrain.getRightDistance();
        
        if(Math.abs(_goal-left) > Math.abs(_goal-right)) {
            _dist = left;
        }
        else {
            _dist = right;
        }
	
        
        if (_goal > 0) {
            _driveTrain.tankDrive(_driveSpeed, _driveSpeed);
        }
        else {
            _driveTrain.tankDrive(-_driveSpeed, -_driveSpeed);
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
