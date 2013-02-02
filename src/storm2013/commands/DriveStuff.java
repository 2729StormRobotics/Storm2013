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
        addSequential(new EncoderDrive(1411.0, 0.6, 0.5)); // forward
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderDrive(-1008.0, 0.6, 0.5)); // back
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderTurn(-1923.0, 0.6, 0.5)); // right
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderTurn(2109.0, 0.6, 0.5)); // left
        addSequential(new DoNothing(),0.5);
    }

}
