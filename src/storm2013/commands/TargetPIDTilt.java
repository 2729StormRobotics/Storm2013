package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/**
 *
 * @author evan1026
 */
public class TargetPIDTilt extends TargetPIDCommand {
    public TargetPIDTilt(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.Y,timeout,continuous,
              0, 0, 0, // P, I, D
              1,10);
        requires(Robot.tilter);
    }
    
    protected double returnPIDInput() {
        return Robot.tilter.getAngle();
    }

    protected void writePIDOut(double output) {
        output /= 10;
        Robot.tilter.move(output);
    }

    protected double computeSetpoint(double newValue) {
        return returnPIDInput()+newValue;
    }
}
