package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the ball to the firing wheel.
 * @author evan1026
 */
public class Fire extends CommandGroup {
    /**
     * Creates a new instance of Fire.
     */
    public Fire(){
        addSequential(new StartBallFiringMotor());
        addSequential(new DoNothing(), 1);
        addSequential(new StopBallFiringMotor());
    }
}
