package storm2013.commands;

import storm2013.Robot;
import storm2013.utilities.Target;

/**
 * Turns the robot based on camera values and values from the gyro
 * @author evan1026
 */
public class TargetPIDTurn extends TargetPIDCommand {    
    /**
     * It's a constructor
     * @param target The {@link Target} at which we're aiming
     * @param timeout How long (seconds) before the command times out
     * @param continuous True if the command will actually finish (stop running) and false otherwise
     */
    public TargetPIDTurn(Target target, double timeout, boolean continuous){
        super(target,Target.Axis.X,timeout,continuous,
              0.5, 0.1, 1, // P, I, D
              2,0.6);
        requires(Robot.driveTrain);
    }
    
    /**
     * Gets the angle from the gyro to be used for the PID controller
     * @return the gyro angle
     */
    protected double returnPIDInput() {
        return Robot.driveTrain.getGyroAngle();
    }

    /**
     * Uses the value from the PID controller to turn the robot.
     * @param output the value to use (between -6 and 6)
     */
    protected void writePIDOut(double output) {
        Robot.driveTrain.tankDrive(output, -output);
    }

    protected void useCameraValue(double value) {
        setSetpoint(returnPIDInput()+value);
    }
}
