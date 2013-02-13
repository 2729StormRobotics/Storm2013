/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/**
 *
 * @author evan1026
 */
public class TargetPIDTurn extends TargetPIDCommand {    
    public TargetPIDTurn(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.X,timeout,continuous,
              0.01, 0, 0.1, // P, I, D
              2,6);
        requires(Robot.driveTrain);
    }
    
    protected double returnPIDInput() {
        return Robot.driveTrain.getGyroAngle();
    }

    protected void writePIDOut(double output) {
        output /= 10;
        Robot.driveTrain.tankDrive(output, -output);
        System.out.println("Turning @ " + output);
    }
}