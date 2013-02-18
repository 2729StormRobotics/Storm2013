package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import storm2013.utilities.Target;

/**
 * Drive from the right side of pyramid, then aim & shoot.
 * @author Joe
 */
public class ShootPyramidRight extends CommandGroup {
    public ShootPyramidRight() {
        addParallel(new LowerTilter(),1);
        addSequential(new EncoderDrive(300.0, 0.5));
        addSequential(new DoNothing(),0.2);
        addSequential(new GyroTurn(-45, 0.5));
        addSequential(new DoNothing(),0.5);
        addParallel(new TargetPIDTurn(Target.ThreePT, 1.0, false));
        addParallel(new TargetPIDTilt(Target.ThreePT, 1.0, false));
        addSequential(new WaitForChildren());
        // 4 in case of jam.
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new SpinDown());
    }
}
