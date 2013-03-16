package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/** Prints out code to be used in a CommandGroup for autonomous movement. */
public class PrintAutonomousMove extends Command {
    private double _speed;
    private double _waitTime;
    
    /**
     * Constructor and such.
     * @param speed    The speed to use for output.
     * @param waitTime The time to wait between actions.
     */
    public PrintAutonomousMove(double speed,double waitTime){
        _speed = speed;
        _waitTime = waitTime;
    }
    
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
    protected void execute() {}
    protected boolean isFinished() {
        return true;
    }
    protected void end() {}
    protected void interrupted() {
        end();
    }
}
