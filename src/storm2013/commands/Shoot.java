package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Spins up the shooter and spins the tomahawk.
 * @author Joe
 */
public class Shoot extends CommandGroup {
    
    /**
     * Constructor stuff.
     */
    public Shoot() {
        addSequential(new SpinUp());
        addParallel(new SpinTomahawk());
    }
}
