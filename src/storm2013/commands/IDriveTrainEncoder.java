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
    public double getStraightDistance();
    public double getTurnDistance();
    public double getLeftDistance();
    public double getRightDistance();
    public void clearEncoder(); //Use encoder.reset();
}
