/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author evan1026
 */
public class SpinUp extends Command {

    public SpinUp(){
        requires(Robot.shooter);
    }
    
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(2500);
        Robot.shooter.getPIDController().enable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.shooter.getPIDController().onTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
