/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

/**
 * An interface that allows the {@link BangBangController} to get the RPM of the motor.
 * @author evan1026
 */
public interface IEncoderSpeed {
    
    /**
     * Gets the RPM of the motor.
     * @return The motor's RPM.
     */
    public double getRPM();
}
