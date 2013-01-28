/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author evan1026
 */
public class PrintEncoders extends Command {

    private IDriveTrainEncoder _encoder;
    private double _speed;
    private String _encoderName;
    private double _decelThreshold;
    private double _tolerance;
    
    public PrintEncoders(IDriveTrainEncoder encoder, double speed, String encoderName, 
            double decelThreshold, double tolerance){
        _encoder = encoder;
        _speed = speed;
        _encoderName = encoderName;
        _decelThreshold = decelThreshold;
        _tolerance = tolerance;
    }
    
    protected void initialize() {
        String out = "addSequential(new ";
        double left = _encoder.getLeftDistance();
        double right = _encoder.getRightDistance();
        
        if ((left < 0) != (right < 0)){
            out += "new EncoderTurn(";
        }
        else {
            out += "new EncoderDrive(";
        }
        
        out += (right < 0) ? "-" : "";
        
        out += (Math.abs(left) < Math.abs(right)) ? left : right;
        out += ", " + _speed + ", " + _encoderName + ", " + _decelThreshold + ", " + _tolerance + "));";
        System.out.println(out);
        
        _encoder.clearEncoder();
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
