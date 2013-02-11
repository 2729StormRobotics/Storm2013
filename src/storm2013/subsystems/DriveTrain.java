package storm2013.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.RobotMap;
import storm2013.commands.ArcadeDrive;

/**
 *
 * @author Joe
 */

public class DriveTrain extends Subsystem{
    
//    private double ACCEL_RATE =1.7;
//    private double DECEL_RATE = 1.7;
    
    Jaguar _left  = new Jaguar(RobotMap.PORT_MOTOR_DRIVE_LEFT),
           _right = new Jaguar(RobotMap.PORT_MOTOR_DRIVE_RIGHT);
    
//    AccelerationLimiter _leftControl = new AccelerationLimiter(_left, ACCEL_RATE, DECEL_RATE);
//    AccelerationLimiter _rightControl = new AccelerationLimiter(_right, ACCEL_RATE, DECEL_RATE);
    
    RobotDrive _drive = new RobotDrive(_left,_right);
    
    Encoder _leftEncoder =  new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
    Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
     
    Gyro _gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
    
    public DriveTrain() {
	
	_leftEncoder.start();
	_rightEncoder.start();
	
//        LiveWindow.addActuator("Drive Train", "Left Motor", _left);
//        LiveWindow.addActuator("Drive Train", "Right Motor", _right);
        LiveWindow.addSensor  ("Drive Train", "Gyro", _gyro);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ArcadeDrive());
    }
    
    public void arcadeDrive(double speed,double turn) {
        _drive.arcadeDrive(-speed, turn, false);
//	_drive.tankDrive(-speed, -speed);
    }
    
    public void tankDrive(double leftValue, double rightValue){
        _drive.tankDrive(-leftValue, -rightValue);
    }

     public double getLeftDistance() {
	return -_leftEncoder.get();
    }

    public double getRightDistance() {
	return _rightEncoder.get();
    }
    public void clearEncoder(){
	_leftEncoder.reset();
	_rightEncoder.reset();
    }
    public void clearGyro(){
        _gyro.reset();
    }
    public double getGyroAngle(){
        return _gyro.getAngle();
    }
}
