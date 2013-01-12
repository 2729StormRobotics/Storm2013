/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
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
    private IDriveTrainEncoder _encoder;
    private DriveTrain _driveTrain = Robot.driveTrain;
    
    public EncoderDrive(double goal, double driveSpeed,IDriveTrainEncoder encoder){ 
        _goal = goal;
        _driveSpeed = driveSpeed;
        _encoder = encoder;
        requires(Robot.driveTrain);
    }
    
    
    public void execute() {
        _dist = _encoder.getDistance();
        
        if (Math.abs(_dist) < Math.abs(_goal)){
            if (_goal > 0){
                _driveTrain.tankDrive(_driveSpeed, _driveSpeed);
            }
            else{
                _driveTrain.tankDrive(-_driveSpeed, -_driveSpeed);
            }
        }
        else if (Math.abs(_dist) > Math.abs(_goal)){
            if (_goal > 0){
                _driveTrain.tankDrive(-_driveSpeed, -_driveSpeed);
            }
            else{
                _driveTrain.tankDrive(_driveSpeed, _driveSpeed);
            }
        }
        else{
            _driveTrain.tankDrive(0, 0);
        }
    }
    public boolean isFinished() {
        return (_dist == _goal);
    }
    public void end() {
    }
    
    public void interrupted() {
    }

    protected void initialize() {
        
    }
    
}
