/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/**
 *Drives a certain number of wheel rotations based on an encoder. The encoder values
 * are supplied by the {@link IDriveTrainEncoder}, which is implemented by some other class
 * to supply values in rotations. It then drives at the specified drive speed until
 * until it reaches the goal.
 * 
 * @author evan1026
 */

public class EncoderDrive extends Command {

    private double _goal;
    private double _dist;
    private double _driveSpeed;
    private double _decelThreshold;
    private double _stopThreshold;
    private IDriveTrainEncoder _encoder;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    /**
     * Main constructor for the class. More could be created if it proves to be worthwhile,
     * but for now, use this one.
     * 
     * @param goal            How far you want to go (use rotations for units, just to make a standard).
     * @param driveSpeed      The speed at which you want to travel (also in rotations).
     * @param encoder         The class that implements {@link IDriveTrainEncoder}.
     * @param decelThreshold  The threshold to determine when to start to decelerate.
     * @param stopThreshold   The threshold to determine when to stop the command.
     * @see   IDriveTrainEncoder
     */
    public EncoderDrive(double goal, double driveSpeed, IDriveTrainEncoder encoder,
            double decelThreshold, double stopThreshold){ 
        _goal = goal;
        _driveSpeed = driveSpeed;
        _encoder = encoder;
        _decelThreshold = decelThreshold;
        _stopThreshold = stopThreshold;
        requires(Robot.driveTrain);
    }
    
    /**
     * Implemented from {@link Command}, but it doesn't actually do anything at the moment.
     */
     protected void initialize() {
    }
    
    /**
     * Implemented from {@link Command}. It is run over and over and such, and it calculates
     * what the drive speed the robot needs to accelerate toward by setting values in 
     * the {@link DriveTrain}.
     * 
     * @see DriveTrain
     */
    public void execute() {
        _dist = _encoder.getDistance();
        
        if(Math.abs(_goal) - Math.abs(_dist) > _decelThreshold){
            if (_dist < _goal){
                _driveTrain.tankDrive(_driveSpeed, _driveSpeed);
            }
            else if (_dist > _goal){
                _driveTrain.tankDrive(-_driveSpeed, -_driveSpeed);
            }
        }
        else{
            _driveTrain.tankDrive(0, 0);
        }  
    }
    
    /**
     * Implemented from {@link Command}. When it returns true, it runs {@link #end()},
     * and then stops running the command.
     * 
     * @return true if it is within the stopping threshold ({@link #_stopThreshold}) and false otherwise.
     */
    public boolean isFinished() {
        return (Math.abs(_goal) - Math.abs(_dist) <= _stopThreshold);
    }
    /**
     * Implemented from {@link Command}. This is the last thing run before the command
     * stops running. At the moment, it doesn't actually do anything.
     */
    public void end() {
    }
    
    /**
     * Implemented from {@link Command}. When {@link Command#cancel()} is run, 
     * this is first called, then {@link #end()}.
     */
    public void interrupted() {
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
   
    //No set distance. You should implement that in the encoder.
   
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
    
    /**
     * Gets the threshold at which the robot will start to decelerate.
     * @return The threshold, a distance, in revolutions.
     */
    public double getDecelThreshold() {
        return _decelThreshold;
    }
    
    /**
     * Sets a new threshold to decide when the robot will decelerate.
     * @param decelThreshold The new deceleration threshold, in revolutions.
     */
    public void setDecelThreshold(double decelThreshold) {
        _decelThreshold = decelThreshold;
    }
    
    /**
     * Gets the threshold at which the robot will stop executing the command.
     * @return The threshold, in revolutions.
     */
    public double getStopThreshold() {
        return _stopThreshold;
    }
    
    /**
     * Sets a new threshold at which the robot will stop.
     * @param stopThreshold The new threshold, in revolutions
     */
    public void setStopThreshold(double stopThreshold) {
        _stopThreshold = stopThreshold;
    }
    
    
}
