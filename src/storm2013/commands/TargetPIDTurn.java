package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/** 
 * Does horizontal target tracking (turning the robot). It reads its target
 * angle from the camera, then uses the gyro as feedback to reach that angle.
 */
public class TargetPIDTurn extends TargetPIDCommand {    
    /** see {@link TargetPIDCommand} */
    public TargetPIDTurn(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.X,timeout,continuous,
              0.5, 0.1, 1, // P, I, D
              4,0.7,       // tolerance, maxOutput (higher because of carpet)
              1.0/20);     // period
        requires(Robot.driveTrain);
    }
    
    protected double returnPIDInput() {
        return Robot.driveTrain.getGyroAngle();
    }
    protected void writePIDOut(double output) {
        Robot.driveTrain.tankDrive(output, -output);
    }
    protected void useCameraValue(double value) {
        setSetpoint(returnPIDInput()+value);
    }
}
