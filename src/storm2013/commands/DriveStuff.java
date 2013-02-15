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
        addSequential(new EncoderDrive(1170.0, 0.5));
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderDrive(-2500.0, 0.5));
        addSequential(new DoNothing(),0.5);
        addSequential(new GyroTurn(-188.92755396326905, 0.4));
        addSequential(new DoNothing(),0.5);
    }

}
