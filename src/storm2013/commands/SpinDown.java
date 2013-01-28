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
public class SpinDown extends Command {

    public SpinDown(){
        requires(Robot.shooter);
    }
    
    protected void initialize() {
        Robot.shooter.getPIDController().setSetpoint(0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.shooter.onTarget();
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
