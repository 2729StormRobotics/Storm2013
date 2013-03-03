/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitForChildren;
import storm2013.Robot;
import storm2013.commands.LowerTilter;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.TargetPIDTurn;
import storm2013.subsystems.Vision;
import storm2013.utilities.Target;

/**
 *
 * @author Joe
 */
public class JustShoot extends CommandGroup {
    public JustShoot() {
        addParallel(new Command() {
            {
                requires(Robot.vision);
            }
            protected void initialize() {
                Robot.vision.setDistance(Vision.Distance.NEAR);
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        addSequential(new LowerTilter(),1);
        addParallel(new TargetPIDTilt(Target.ThreePT, 1.0, false));
        addParallel(new TargetPIDTurn(Target.ThreePT,1.0,false));
        addSequential(new WaitForChildren());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new SpinDown());
    }
}
