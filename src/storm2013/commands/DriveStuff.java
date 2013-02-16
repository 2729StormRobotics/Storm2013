package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous movement {@link CommandGroup}
 * @author Storm
 */
public class DriveStuff extends CommandGroup {
    /**
     * Default constructor.
     */
    public DriveStuff() {
        addSequential(new EncoderDrive(1170.0, 0.5));
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderDrive(-2500.0, 0.5));
        addSequential(new DoNothing(),0.5);
        addSequential(new GyroTurn(-188.92755396326905, 0.4));
        addSequential(new DoNothing(),0.5);
    }

}
