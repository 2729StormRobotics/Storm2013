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
public class PrintAutonomousMove extends Command {

    private double _speed;
    private double _waitTime;
    
    public PrintAutonomousMove( double speed, double tolerance,
	    double waitTime){
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

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
