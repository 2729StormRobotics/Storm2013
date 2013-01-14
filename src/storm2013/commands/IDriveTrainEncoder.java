/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

/**
 * An interface that needs to be implemented in order for {@link EncoderDrive} to work.
 * It simply supplies the distance traveled based on encoder values.
 * 
 * @author evan1026
 */
public interface IDriveTrainEncoder {
   
    /**
     * Will calculate the total distance traveled based on encoder data
     * @return The total distance traveled, in revolutions.
     */
    public double getDistance();
}
