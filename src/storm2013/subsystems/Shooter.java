/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import storm2013.utilities.IEncoderSpeed;

import storm2013.RobotMap;

/**
 *
 * @author Storm
 */
public class Shooter extends Subsystem implements IEncoderSpeed{
    
    DigitalInput hallEffect;
    public Counter counter;
    Jaguar wheelMotor = new Jaguar(RobotMap.PORT_MOTOR_SHOOTER);
    
    double counterPeriod;
    
    /**
     * @see IEncoder
     */
    public Shooter(){
	hallEffect = new DigitalInput(RobotMap.PORT_HALL_EFFECT);
	counter = new Counter(	CounterBase.EncodingType.k1X, //Count only rising edge of digital signal
				hallEffect,
				hallEffect,
				false); //inverted
	
	counter.clearDownSource();
        counter.setUpSourceEdge(true, false); //TODO Check without this
	counter.start();
	
	LiveWindow.addActuator("Shooter", "wheelMotor", wheelMotor);
	LiveWindow.addSensor("Shooter", "wheelMotorRawCounter", counter);
	
    }
    
    
    /**
     * 
     * @return revolutions per minute
     */
    public double getRPM() {
	double tempPeriod = counter.getPeriod();
	if (counterPeriod != tempPeriod && !Double.isInfinite(tempPeriod)){
	    counterPeriod = counter.getPeriod();
	    return 60 / counterPeriod;
	}else{
	    return counterPeriod;
	}
	
    
    }
    
    public void setPower(double power) {
	wheelMotor.set(power);
	SmartDashboard.putNumber("Wheel Motor Power", power);
	
	SmartDashboard.putNumber("Raw Counter", counter.get());
	SmartDashboard.putNumber("Counter Period", counter.getPeriod());
	SmartDashboard.putNumber("Supposed Wheel RPM", getRPM());
	
    }

    protected void initDefaultCommand() {
    }
    
    
}
