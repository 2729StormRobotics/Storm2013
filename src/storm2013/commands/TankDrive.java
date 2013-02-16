package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.Robot;

/**
 * Drives the robot
 * @author Joe
 */
public class TankDrive extends Command {
    
    /**
     * Constructor
     */
    public TankDrive() {
        requires(Robot.driveTrain);
    }

    /**
     * Initializes {@link Command}
     */
    protected void initialize() {
    }

    /**
     * Does some tank driving
     */
    protected void execute() {
        double left  = Robot.oi.getLeftDrive(),
               right = Robot.oi.getRightDrive();
        SmartDashboard.putNumber("Left",  left);
        SmartDashboard.putNumber("Right", right);
        Robot.driveTrain.tankDrive(left,right);
    }

    /**
     * Never ends
     * @return false
     */
    protected boolean isFinished() {
        return false;
    }

    /**
     * Called when {@link Command} is over. Does nothing
     */
    protected void end() {
    }

    /**
     * Ends {@link Command}
     */
    protected void interrupted() {
        end();
    }
}
