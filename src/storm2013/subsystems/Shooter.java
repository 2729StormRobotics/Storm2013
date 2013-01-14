/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase;

import storm2013.utilities.IEncoderSpeed;

import storm2013.RobotMap;

/**
 *
 * @author Storm
 */
public class Shooter implements IEncoderSpeed{
    
    DigitalInput hallEffect;
    Counter counter;
    
    /**
     * @see IEncoder
     */
    public Shooter(){
	
	hallEffect = new DigitalInput(RobotMap.PORT_HALL_EFFECT);
	counter = new Counter(	CounterBase.EncodingType.k1X, //Count only rising edge of digital signal
				hallEffect,
				hallEffect,
				false); //inverted
	
	//counter.clearDownSource();
        //counter.setUpSourceEdge(true, false); //TODO Check without this
	counter.start();
    }
    
    
    /**
     * 
     * @return revolutions per minute
     */
    public double getRPM() {
	return 60 /counter.getPeriod();
    }
    
}
