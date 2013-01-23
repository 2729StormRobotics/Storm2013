/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.RobotMap;
import storm2013.utilities.Accelerator;
import storm2013.utilities.BangBangController;
import storm2013.utilities.HallEffectSpeedSensor;

/**
 *
 * @author Storm
 */
public class Shooter extends PIDSubsystem {
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_HALL_EFFECT);
    private Jaguar _wheelMotor = new Jaguar(RobotMap.PORT_MOTOR_SHOOTER);
    private Accelerator _accelerator = new Accelerator(_wheelMotor);
//    private BangBangController _bangBang = new BangBangController(_wheelMotor, _speedSensor, 0, 1000, 0.5, 1.0, 1.0/60);

    public Shooter(){
        super(0,0,0);
        _speedSensor.setMinSpeedRpm(20);
        LiveWindow.addSensor  ("Shooter", "Speed Sensor",     _speedSensor);
        LiveWindow.addActuator("Shooter", "PID Control", getPIDController());
    }
    
    public double getSpeedRpm() {
        return _speedSensor.getSpeedRpm();
    }
    
    public double getMotorVal() {
        return _wheelMotor.get();
    }

    protected void initDefaultCommand() {}

    protected double returnPIDInput() {
        return _speedSensor.getSpeedRpm();
    }

    protected void usePIDOutput(double output) {
        _accelerator.set(output);
    }
}
