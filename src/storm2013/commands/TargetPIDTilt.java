package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/**
 * Does vertical target tracking (with the tilter). Uses the camera as a sensor,
 * so this command is susceptible to network latency.
 */
public class TargetPIDTilt extends TargetPIDCommand {
    private double _angleToTarget = 0;
    /** See {@link TargetPIDCommand}. */
    public TargetPIDTilt(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.Y,timeout,continuous,
              0.7, 0.15, 1, // P, I, D
              0.5,1,        // tolerance, maxOutput
              1.5/10);      // period - high to allow for network latency.
        requires(Robot.tilter);
        setSetpoint(0);
    }
    
    protected double returnPIDInput() {
        return _angleToTarget;
    }
    protected void writePIDOut(double output) {
        Robot.tilter.move(output);
    }
    protected void useCameraValue(double value) {
        _angleToTarget = -value;
    }
}
