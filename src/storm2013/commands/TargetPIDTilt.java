package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/**
 * Tilts the tilter based on the camera stuff
 * @author evan1026
 */
public class TargetPIDTilt extends TargetPIDCommand {
    private double _angleToTarget = 0;
    /**
     * Constructor.
     * @param target The {@link Target} to aim at
     * @param timeout The timeout
     * @param continuous Whether or not the command should be continuous
     */
    public TargetPIDTilt(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.Y,timeout,continuous,
              0.7, 0.15, 1, // P, I, D
              0.5,1,1.5/10);
        requires(Robot.tilter);
        setSetpoint(0);
    }
    
    /**
     * Returns the angle to target
     * @return the angle of the tilter
     */
    protected double returnPIDInput() {
        return _angleToTarget;
    }

    /**
     * Writes the output to the tilter.
     * @param output the output to write
     */
    protected void writePIDOut(double output) {
        Robot.tilter.move(output);
    }

    protected void useCameraValue(double value) {
        _angleToTarget = -value;
    }
}
