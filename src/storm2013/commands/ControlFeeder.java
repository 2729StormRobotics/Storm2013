package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Allows a user to control the feeder given a direction to spin and a time.
 * @author evan1026
 */
public class ControlFeeder extends CommandGroup {
    /**
     * Allows a user to control the feeder given a direction to spin and a time.
     * @param goingUp True to load balls in (move them up) or false to push them out (move them down).
     * @param time    The amount of time (in seconds) for the feeder to spin.
     */
    public ControlFeeder(boolean goingUp, int time){
        addSequential(new StartFeeder(goingUp));
        addSequential(new DoNothing(), time);
        addSequential(new StopFeeder());
    }
}
