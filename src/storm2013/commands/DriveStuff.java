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
	addSequential(new EncoderTurn(-1424.0, 0.6, 0.5)); // right
	addSequential(new DoNothing(),0.5);
	addSequential(new EncoderTurn(1101.0, 0.6, 0.5));  // left
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderDrive(-1287.0, 0.6, 0.5)); // back
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderDrive(1454.0, 0.6, 0.5)); // forward
        addSequential(new DoNothing(),0.5);
        addSequential(new EncoderTurn(-3896.0, 0.6, 0.5)); // really right
        addSequential(new DoNothing(),0.5);
//	addSequential(new EncoderDrive(1320.0,  0.6, 0.5)); //forward
//	addSequential(new DoNothing(), 0.5);
//	addSequential(new EncoderTurn( 5000.0,  0.6, 0.5)); //left
//	addSequential(new DoNothing(), 0.5);
//	addSequential(new EncoderTurn( -1000.0, 0.6, 0.5)); //right
//	addSequential(new DoNothing(), 0.5);
//	addSequential(new EncoderTurn( 3000.0,  1.0, 0.5)); //left fast
//	addSequential(new DoNothing(), 0.5);
//	addSequential(new EncoderDrive(-1675.0, 0.6, 0.5)); //back
    }

}
