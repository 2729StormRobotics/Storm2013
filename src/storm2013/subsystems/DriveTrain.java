/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.commands.ArcadeDrive;
import storm2013.utilities.AccelerationLimiter;

/**
 *
 * @author Joe
 */
public class DriveTrain extends Subsystem {
    
    private double ACCEL_RATE = 1;
    private double DECEL_RATE = 1;
    
    Victor _left,_right;
    AccelerationLimiter _leftControl = new AccelerationLimiter(_left, ACCEL_RATE, DECEL_RATE);
    AccelerationLimiter _rightControl = new AccelerationLimiter(_right, ACCEL_RATE, DECEL_RATE);
    RobotDrive _drive = new RobotDrive(_leftControl,_rightControl);
    

    public DriveTrain() {
        LiveWindow.addActuator("Drive Train", "Left Motor", _left);
        LiveWindow.addActuator("Drive Train", "Left Motor", _right);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void arcadeDrive(double speed,double turn) {
        _drive.arcadeDrive(speed, turn);
    }
    
    public void tankDrive(double leftValue, double rightValue){
        _drive.tankDrive(leftValue, rightValue);
    }
}
