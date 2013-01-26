/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author evan1026
 */
public class ControlFeeder extends CommandGroup {
    public ControlFeeder(boolean goingUp, int time){
        addSequential(new StartFeeder(goingUp));
        addSequential(new DoNothing(), time);
        addSequential(new StopFeeder());
    }
}
