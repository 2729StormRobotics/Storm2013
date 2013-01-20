package storm2013.utilities;

/**
 * A speed sensor intended for use with flywheels
 * @author evan1026
 */
public interface ISpeedSensor {
    
    /**
     * Reads the speed of the motor.
     * @return The motor's speed in RPM.
     */
    public double getSpeedRpm();
}
