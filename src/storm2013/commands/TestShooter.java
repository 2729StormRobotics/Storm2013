package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author Storm
 */
public class TestShooter extends Command {
    
    public TestShooter() {
        requires(Robot.shooter);
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.shooter.setPower(-Robot.oi.driveJoystick.getRawAxis(1));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
}
