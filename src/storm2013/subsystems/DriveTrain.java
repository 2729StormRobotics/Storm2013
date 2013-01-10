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

/**
 *
 * @author Joe
 */
public class DriveTrain extends Subsystem {
    Victor _left,_right;
    RobotDrive _drive = new RobotDrive(_left,_right);

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
}
