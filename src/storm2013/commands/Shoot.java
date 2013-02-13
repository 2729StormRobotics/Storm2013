/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import storm2013.Robot;

/**
 *
 * @author Joe
 */
public class Shoot extends CommandGroup {
    
    public Shoot() {
        addSequential(new SpinUp());
        addParallel(new SpinTomahawk());
    }
}
