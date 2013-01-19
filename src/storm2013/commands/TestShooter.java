package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.subsystems.Shooter;

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
	Robot.shooter.setPower(-Robot.oi.driveJoystick.getRawAxis(4));
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
