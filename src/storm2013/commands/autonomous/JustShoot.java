package storm2013.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2013.Robot;
import storm2013.commands.GyroTurn;
import storm2013.commands.LEDcommands.Color;
import storm2013.commands.LEDcommands.SetColor;
import storm2013.commands.LowerTilter;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.TiltSetDistance;
import storm2013.subsystems.Vision;
import storm2013.utilities.Target;

/**
 * This autonomous mode just shoots a full clip; we use it from either back
 * corner of the pyramid.
 */
public class JustShoot extends CommandGroup {
    public JustShoot() {
        addSequential(new Command() {
            public void initialize() {
                new SetColor(new Color(0,0,255)).start();
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        addSequential(new TiltSetDistance(1, Vision.Distance.NEAR));
        // Align the tilter with the target
        addSequential(new TargetPIDTilt(Target.ThreePT, 1.0, false));
        // Shoot repeatedly (in case of jams)
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        // Stop shooting (not entirely necessary -- disabled mode would spin it
        // down anyway)
        addSequential(new SpinDown());
    }
}
