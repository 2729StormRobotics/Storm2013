package storm2013.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.RobotMap;
import storm2013.commands.TankDrive;

/** Subsystem including the drive motors, encoders, and gyro. */

public class DriveTrain extends Subsystem {
    
    Jaguar _left  = new Jaguar(RobotMap.PORT_MOTOR_DRIVE_LEFT),
           _right = new Jaguar(RobotMap.PORT_MOTOR_DRIVE_RIGHT);
    
    RobotDrive _drive = new RobotDrive(_left,_right);
    
    Encoder _leftEncoder =  new Encoder(RobotMap.PORT_ENCODER_LEFT_1, RobotMap.PORT_ENCODER_LEFT_2);
    Encoder _rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, RobotMap.PORT_ENCODER_RIGHT_2);
     
    Gyro _gyro = new Gyro(RobotMap.PORT_SENSOR_GYRO);
    
    public DriveTrain() {
	
        _leftEncoder.start();
        _rightEncoder.start();
	
        LiveWindow.addSensor  ("Drive Train", "Gyro", _gyro);
        LiveWindow.addSensor  ("Drive Train", "Left Encoder", _leftEncoder);
        LiveWindow.addSensor  ("Drive Train", "Right Encoder", _rightEncoder);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
    
    /**
     * Drives in arcade mode (think the old game "Asteroid").
     * @param speed Forward/back (1 = forward,-1 = back)
     * @param turn  (Counter)Clockwise (1 = clockwise,-1 = counterclockwise)
     */
    public void arcadeDrive(double speed,double turn) {
        _drive.arcadeDrive(-speed, turn, false);
    }
    
    /** Drives in tank mode (each motor gets 1 = forward,-1 = back). */
    public void tankDrive(double leftValue, double rightValue){
        _drive.tankDrive(-leftValue, -rightValue);
    }

    /** Reads the left encoder (+ = forward,- = back). */
     public double getLeftDistance() {
        return _leftEncoder.get();
    }

     /** Reads the right encoder (+ = forward,- = back). */
    public double getRightDistance() {
        return -_rightEncoder.get();
    }
    
    /** Resets both encoders. */
    public void clearEncoder(){
        _leftEncoder.reset();
        _rightEncoder.reset();
    }
    
    /** Resets the gyro. */
    public void clearGyro(){
        _gyro.reset();
    }
    
    /**
     * Reads the Robot's (relative) heading from gyro (+ = clockwise,
     * - = counterclockwise).
     */
    public double getGyroAngle(){
        return _gyro.getAngle();
    }
    
    /** Reads the [-1,1] value set for the left wheels. */
    public double getLeftSpeed(){
        return _left.get();
    }
    
    /** Reads the [-1,1] value set for the right wheels. */
    public double getRightSpeed(){
        return _right.get();
    }
}
