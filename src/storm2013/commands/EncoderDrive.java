/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author evan1026
 */

public class EncoderDrive extends Command {

    
    private double _leftGoal;
    private double _rightGoal;
    private double _leftDist;
    private double _rightDist;
    private double _driveSpeed;
    private IDriveTrainEncoders _encoders;
    private SpeedController _leftController;
    private SpeedController _rightController;
    
    public EncoderDrive(double leftGoal, double rightGoal, double driveSpeed,
            IDriveTrainEncoders encoders, SpeedController leftController,
            SpeedController rightController){ 
        _leftGoal = leftGoal;
        _rightGoal = rightGoal;
        _driveSpeed = driveSpeed;
        _leftDist = 0;
        _rightDist = 0;
        _encoders = encoders;
        _leftController = leftController;
        _rightController = rightController;
    }
    
    public void setLeftDist_(double leftDist) {
        _leftDist = leftDist;
    }
    public void setRightDist_(double rightDist) {
        _rightDist = rightDist;
    }
    public void setLeftGoal_(double leftGoal){
        _leftGoal = leftGoal;
    }
    public void setRightGoal_(double rightGoal){
        _rightGoal = rightGoal;
    }
    public double getLeftGoal() {
        return _leftGoal;
    }
    public double getRightGoal() {
        return _rightGoal;
    }
    public double getLeftDist() {
        return _leftDist;
    }
    public double getRightDist() {
        return _rightDist;
    }
    
    public void execute() {
        _leftDist = _encoders.getLeftDistance();
        _rightDist = _encoders.getRightDistance();
        
        if(_leftDist < _leftGoal){
            _leftController.set(_driveSpeed); //I'll get to acceleration and that other stuff later
        }
        else if(_leftDist > _leftGoal){
            _leftController.set(-_driveSpeed);
        }
        
        if(_rightDist < _rightGoal){
            _rightController.set(_driveSpeed);
        }
        else if(_rightDist > _rightGoal){
            _rightController.set(-_driveSpeed);
        }
        
    }
    public boolean isFinished() {
        return (_leftDist == _leftGoal && _rightDist == _rightGoal);
    }
    public void end() {
    }
    
    public void interrupted() {
    }

    protected void initialize() {
        
    }
    
}
