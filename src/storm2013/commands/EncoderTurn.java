/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

/**
 *
 * @author evan1026
 */
public class EncoderTurn extends Command {

    private double _goal;
    private double _dist;
    private double _turnSpeed;
    private double _decelThreshold;
    private double _tolerance;
    private IDriveTrainEncoder _encoder;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    public EncoderTurn(double goal, double turnSpeed, IDriveTrainEncoder encoder,
            double decelThreshold, double tolerance){ 
        
        if (encoder == null){
            throw new NullPointerException("The DriveTrainEncoder cannot be null.");
        }
        
        _goal = goal;
        _turnSpeed = turnSpeed;
        _encoder = encoder;
        _decelThreshold = decelThreshold;
        _tolerance = tolerance;
        requires(Robot.driveTrain);
    }
    
    protected void initialize() {
    }

    protected void execute() {
        _dist = _encoder.getTurnDistance();
        
        if(Math.abs(_goal) - Math.abs(_dist) > _decelThreshold){
            if (_dist < _goal){
                _driveTrain.tankDrive(-_turnSpeed, _turnSpeed);
            }
            else if (_dist > _goal){
                _driveTrain.tankDrive(_turnSpeed, -_turnSpeed);
            }
        }
        else{
            _driveTrain.tankDrive(0, 0);
        }
    }

    protected boolean isFinished() {
        return (Math.abs(_goal) - Math.abs(_dist) <= _tolerance);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
    public double getGoal() {
        return _goal;
    }
    
    public void setGoal(double goal) {
        _goal = goal;
    }

    public double getDist() {
        return _dist;
    }
   
    public double getDriveSpeed() {
        return _turnSpeed;
    }
    
    public void setTurnSpeed(double driveSpeed) {
        _turnSpeed = driveSpeed;
    }
    
    public double getDecelThreshold() {
        return _decelThreshold;
    }
    
    public void setDecelThreshold(double decelThreshold) {
        _decelThreshold = decelThreshold;
    }
    
    public double getTolerance() {
        return _tolerance;
    }
    
    public void setTolerance(double stopThreshold) {
        _tolerance = stopThreshold;
    }
}
