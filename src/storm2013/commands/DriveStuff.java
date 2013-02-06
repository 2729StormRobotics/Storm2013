/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Storm
 */
public class DriveStuff extends CommandGroup {
    public DriveStuff() {
       addSequential(new EncoderDrive(1861.0, 0.6, 0.5));
       addSequential(new DoNothing(),0.5);
       addSequential(new EncoderTurn(628.0, 0.6, 0.5));
       addSequential(new DoNothing(),0.5);
       addSequential(new EncoderTurn(-575.0, 0.6, 0.5));
       addSequential(new DoNothing(),0.5);
       addSequential(new EncoderDrive(2005.0, 0.6, 0.5));
       addSequential(new DoNothing(),0.5);
       addSequential(new EncoderTurn(-124.0, 0.6, 0.5));
       addSequential(new DoNothing(),0.5);
       addSequential(new EncoderTurn(216.0, 0.6, 0.5));
       addSequential(new DoNothing(),0.5);
    }

}
