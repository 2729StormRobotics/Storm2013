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
public class MoveTilter extends Command {
    public MoveTilter() {
        requires(Robot.tilter);
    }

    protected void initialize() {}

    protected void execute() {
        Robot.tilter.move(Robot.oi.getTilterAxis()*Robot.tilter.SPEED_DEFAULT);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
        end();
    }
}
