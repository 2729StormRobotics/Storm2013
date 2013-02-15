package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Prints out values based on teleoperated movement that can be used for an
 * autonomouse.
 * @author evan1026
 */
public class PrintAutonomousMove extends Command {

    private double _speed;
    private double _waitTime;
    
    /**
     * Constructor and such.
     * @param speed The speed to move at when doing these autonomously.
     * @param waitTime The amount of time to wait in between different movement commands.
     */
    public PrintAutonomousMove(double speed,double waitTime){
        _speed = speed;
        _waitTime = waitTime;
    }
    
    /**
     * Initializes the {@link Command}. This actually does all of the logic, and
     * execute does nothing.
     */
    protected void initialize() {
        String out = "addSequential(new ";
        double left = Robot.driveTrain.getLeftDistance();
        double right = Robot.driveTrain.getRightDistance();
        double turn = Robot.driveTrain.getGyroAngle();
        
        if ((left < 0) != (right < 0)){
            out += "GyroTurn(";
            out += turn;
        }
        else {
            out += "EncoderDrive(";
            out += (right < 0) ? "-" : "";
            out += (Math.min(Math.abs(left), Math.abs(right)));
        }
        
        out += ", " + _speed + "));";
        System.out.println(out);
	
        System.out.println("addSequential(new DoNothing()," + _waitTime + ");");
        
        Robot.driveTrain.clearEncoder();
    }

    /**
     * Does nothing.
     */
    protected void execute() {
    }

    /**
     * Tells the robot to end the {@link Command}.
     * @return true, so the {@link Command} ends immediately.
     */
    protected boolean isFinished() {
        return true;
    }

    /**
     * Does nothing.
     */
    protected void end() {
    }

    /**
     * Calls {@link #end()}
     */
    protected void interrupted() {
        end();
    }
    
}
