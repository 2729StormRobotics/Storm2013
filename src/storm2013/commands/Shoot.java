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
public class Shoot extends CommandGroup {
    /**
     * 
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
