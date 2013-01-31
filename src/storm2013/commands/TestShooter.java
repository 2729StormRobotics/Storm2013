package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	Robot.shooter.disable();
    }

    protected void execute() {
	SmartDashboard.putNumber("Wheel Speed (RPM)",Robot.shooter.getSpeedRpm());
	Robot.shooter.setMotorValRaw(-(-Robot.oi.getFlipperAxis()+1)/2);
	SmartDashboard.putNumber("Power Value", -Robot.shooter.getMotorValRaw());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
//	Robot.shooter.setSpeedRpm(0);
    }

    protected void interrupted() {
        end();
    }
}
