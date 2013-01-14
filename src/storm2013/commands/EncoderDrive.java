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

public class EncoderDrive extends Command {

    
    private double _goal;
    private double _dist;
    private double _driveSpeed;
    private double _decelThreshold;
    private double _stopThreshold;
    private IDriveTrainEncoder _encoder;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    public EncoderDrive(double goal, double driveSpeed, IDriveTrainEncoder encoder,
            double decelThreshold, double stopThreshold){ 
        _goal = goal;
        _driveSpeed = driveSpeed;
        _encoder = encoder;
        _decelThreshold = decelThreshold;
        _stopThreshold = stopThreshold;
        requires(Robot.driveTrain);
    }
    
     protected void initialize() {
    }
    
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
    public boolean isFinished() {
        return (Math.abs(_goal) - Math.abs(_dist) <= _stopThreshold);
    }
    public void end() {
    }
    
    public void interrupted() {
    }

   
    
}
