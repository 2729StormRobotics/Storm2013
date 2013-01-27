package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A control group that fires some number of times.
 * @author evan1026
 */
public class Shoot extends CommandGroup {
    /**
     * Sets up all the commands to do the firing and stuff.
     * @param number The number of times to fire
     */
    public Shoot(int number){
        addSequential(new SpinUp());
        for(int i = 0; i < number; i++){
            addSequential(new LoadBall());
            addSequential(new Fire());
            addSequential(new WaitUntilFullSpeed());
        }
        addSequential(new SpinDown());
    }
}
