package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Storm
 */
public class DonaldDance extends CommandGroup {
    class Forward extends Drive {
	Forward() {
	    super(0.7,-0.5);
	}
    }
    class Backward extends Drive {
	Backward() {
	    super(-0.7,0.5);
	}
    }
    class Left extends Drive {
	Left() {
	    super(0,-1);
	}
    }
    class Right extends Drive {
	Right() {
	    super(0,1);
	}
    }
    class Wait extends DoNothing {}
    
    public DonaldDance() {
	addSequential(new Forward(),2);
	addSequential(new Wait(),.5);
	addSequential(new Right(),.8);
	addSequential(new Wait(),.5);
	addSequential(new Left(),1.6);
	addSequential(new Wait(),.5);
	addSequential(new Drive(0,0.8),6.2);
//	addSequential(new Wait(),.5);
//	addSequential(new Forward(),2);
//	addSequential(new Wait(),.5);
//	addSequential(new Backward(),3);
	
    }
}
