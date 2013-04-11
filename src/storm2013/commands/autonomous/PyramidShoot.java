package storm2013.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.StartCommand;
import storm2013.Robot;
import storm2013.commands.GyroTurn;
import storm2013.commands.LEDcommands.Color;
import storm2013.commands.LEDcommands.SetColor;
import storm2013.commands.LowerTilter;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.TiltSetDistance;
import storm2013.subsystems.Tilter;
import storm2013.subsystems.Vision;
import storm2013.utilities.Target;

/**
 * This autonomous mode moves back and shoots; we use it from the back-center
 * of the pyramid.
 */
public class PyramidShoot extends CommandGroup {
    
    public PyramidShoot() {
        // Drive back. This used to be an EncoderDrive, but our right encoder
        // is broken.
        addSequential(new Command() {
            {
                requires(Robot.driveTrain);
            } 
            protected void initialize() {
                Robot.driveTrain.clearEncoder();
            }
            protected void execute() {
                Robot.driveTrain.tankDrive(-0.6, -0.6);
            }
            protected boolean isFinished() {
                return Robot.driveTrain.getLeftDistance() < -1476;
            }
            protected void end() {
                Robot.driveTrain.tankDrive(0, 0);
            }
            protected void interrupted() {
                end();
            }
        });
//        addSequential(new StartCommand(new SetColor(new Color(0,0,255))));
        addSequential(new TiltSetDistance(Tilter.SPEED_DEFAULT, Vision.Distance.NEAR));
        // Align tilter with target
        addSequential(new TargetPIDTilt(Target.ThreePT, 1.0, false));
        // Shoot repreatedly (in case of jams)
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        // End shooting (not always necessary)
        addSequential(new SpinDown());
    }
}
