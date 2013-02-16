package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/**
 * Tilts the tilter based on the camera stuff
 * @author evan1026
 */
public class TargetPIDTilt extends TargetPIDCommand {
    /**
     * Constructor.
     * @param target The {@link Target} to aim at
     * @param timeout The timeout
     * @param continuous Whether or not the command should be continuous
     */
    public TargetPIDTilt(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.Y,timeout,continuous,
              0, 0, 0, // P, I, D
              1,10);
        requires(Robot.tilter);
    }
    
    /**
     * Returns the tilter angle
     * @return the angle of the tilter
     */
    protected double returnPIDInput() {
        return Robot.tilter.getAngle();
    }

    /**
     * Writes the output (divided by 10) to the tilter.
     * @param output the output to write
     */
    protected void writePIDOut(double output) {
        output /= 10;
        Robot.tilter.move(output);
    }

    /**
     * Figures out the new setPoint
     * @param newValue The new value to add to the current input to find the setPoint
     * @return the new setPoint
     */
    protected double computeSetpoint(double newValue) {
        return returnPIDInput()+newValue;
    }
}
