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
    }

    protected void execute() {
//	Robot.shooter.setSpeedRpm(2500);
	SmartDashboard.putNumber("Wheel Speed (RPM)",Robot.shooter.getSpeedRpm());
	System.out.println("Speed: " + Robot.shooter.getSpeedRpm());
//        Robot.shooter.setPower(-Robot.oi.driveJoystick.getRawAxis(1));
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
