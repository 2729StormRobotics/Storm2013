/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2013.utilities.Target;

/**
 *
 * @author Joe
 */
public class JustShoot extends CommandGroup {
    public JustShoot() {
        addSequential(new LowerTilter(),1);
        addSequential(new TargetPIDTilt(Target.ThreePT, 1.0, false));
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new Shoot());
        addSequential(new SpinDown());
    }
}
