/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.Robot;

/**
 *
 * @author Joe
 */
public class TankDrive extends Command {
    
    public TankDrive() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
    }

    protected void execute() {
        double left  = Robot.oi.getLeftDrive(),
               right = Robot.oi.getRightDrive();
        SmartDashboard.putNumber("Left",  left);
        SmartDashboard.putNumber("Right", right);
        Robot.driveTrain.tankDrive(left,right);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
