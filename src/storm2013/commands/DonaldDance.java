package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Storm
 */
public class DonaldDance extends CommandGroup {
    Command forward  = new Drive(0.7,-0.5),
	    backward = new Drive(-0.7,0.5),
	    left     = new Drive(0,-1),
	    right    = new Drive(0,1);
    
    public DonaldDance() {
	addSequential(forward,3);
	addSequential(left,3);
    }
}
