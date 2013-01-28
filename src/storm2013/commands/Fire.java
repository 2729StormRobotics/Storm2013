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
public class Fire extends CommandGroup {
    public Fire(){
        addSequential(new StartBallFiringMotor());
        addSequential(new DoNothing(), 1);
        addSequential(new StopBallFiringMotor());
    }
}
