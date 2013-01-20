/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import storm2013.RobotMap;
import storm2013.utilities.HallEffectSpeedSensor;

/**
 *
 * @author Storm
 */
public class Shooter extends Subsystem {
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_HALL_EFFECT);
    private Jaguar _wheelMotor = new Jaguar(RobotMap.PORT_MOTOR_SHOOTER);

    public Shooter(){
        _speedSensor.setMinSpeedRpm(20);
        LiveWindow.addActuator("Shooter", "Wheel Motor", _wheelMotor);
        LiveWindow.addSensor("Shooter", "Speed Sensor", _speedSensor);
    }
    
    public void setPower(double power) {
        _wheelMotor.set(power);
    }

    protected void initDefaultCommand() {}
}
