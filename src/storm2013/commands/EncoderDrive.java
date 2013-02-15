package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/**
 * Drives a certain number of wheel rotations based on an encoder. The encoder values
 * are supplied by the drive train (see {@link Robot}), which is implemented by some other class
 * to supply values in rotations. It then drives at the specified drive speed until
 * until it reaches the goal.
 * 
 * @author evan1026
 */
public class EncoderDrive extends Command {

    private double _goal;
    private double _dist;
    private double _driveSpeed;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    /**
     * Main constructor for the class. More could be created if it proves to be worthwhile,
     * but for now, use this one.
     * 
     * @param goal            How far you want to go (use rotations for units, just to make a standard).
     * @param driveSpeed      The speed at which you want to travel (also in rotations).
     */
    public EncoderDrive(double goal, double driveSpeed){ 
        
        _goal = goal;
        _driveSpeed = driveSpeed;
        requires(Robot.driveTrain);
    }
    
    /**
     * Implemented from {@link Command}, but it doesn't actually do anything at the moment.
     */
     protected void initialize() {
        Robot.driveTrain.clearEncoder();
     }
    
    /**
     * Implemented from {@link Command}. It is run over and over and such, and it calculates
     * what the drive speed the robot needs to accelerate toward by setting values in 
     * the {@link DriveTrain}.
     * 
     * @see DriveTrain
     */
    public void execute() {
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
    
    /**
     * Implemented from {@link Command}. When it returns true, it runs {@link #end()},
     * and then stops running the command.
     * 
     * @return true if it has reached the goal and false otherwise.
     */
    public boolean isFinished() {
        if(_goal < 0) {
            return _dist <= _goal;
        } 
        else {
            return _dist >= _goal;
        }
    }
    
    /**
     * Implemented from {@link Command}. This is the last thing run before the command
     * stops running. Stops the robot.
     */
    public void end() {
        Robot.driveTrain.tankDrive(0, 0);
    }
    
    /**
     * Implemented from {@link Command}. When {@link Command#cancel()} is run, 
     * this is first called, then {@link #end()}.
     */
    public void interrupted() {
        end();
    }

    /**
     * Gets the current goal.
     * @return The current goal, in rotations.
     */
    public double getGoal() {
        return _goal;
    }
    
    /**
     * Sets a new goal.
     * @param goal The new goal, in rotations.
     */
    public void setGoal(double goal) {
        _goal = goal;
    }
    
    /**
     * Gets the total distance traveled.
     * @return The distance traveled, in revolutions.
     */
    public double getDist() {
        return _dist;
    }
   
    /**
     * Returns the speed at which the robot it set to drive.
     * @return The drive speed, in RPM.
     */
    public double getDriveSpeed() {
        return _driveSpeed;
    }
    
    /**
     * Sets the drive speed.
     * @param driveSpeed The drive speed, in RPM.
     */
    public void setDriveSpeed(double driveSpeed) {
        _driveSpeed = driveSpeed;
    }
}
