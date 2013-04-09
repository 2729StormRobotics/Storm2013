/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import storm2013.Robot;
import storm2013.subsystems.Vision.Distance;

/**
 *
 * @author Joe
 */
public class TiltToCurrentDistance extends TiltForDistance {
    public TiltToCurrentDistance(double power) {
        super(power, Distance.NEAR);
    }

    protected void initialize() {
        _setDistance(Robot.vision.getCurrDistance());
        super.initialize();
    }
}
