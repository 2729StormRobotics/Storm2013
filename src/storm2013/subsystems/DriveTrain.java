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
    
    public DriveTrain() {
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
    
    /** Reads the [-1,1] value set for the left wheels. */
    public double getLeftSpeed(){
        return _left.get();
    }
    
    /** Reads the [-1,1] value set for the right wheels. */
    public double getRightSpeed(){
        return _right.get();
    }
}
