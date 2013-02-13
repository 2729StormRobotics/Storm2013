/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
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
        Robot.driveTrain.tankDrive(Robot.oi.getLeftDrive(), Robot.oi.getRightDrive());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
