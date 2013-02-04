/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.RobotMap;
import storm2013.utilities.Accelerator;
import storm2013.utilities.HallEffectSpeedSensor;

/**
 *
 * @author Storm
 */
public class Shooter extends PIDSubsystem {

    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT);
    private Victor _wheelMotor = new Victor(RobotMap.PORT_MOTOR_SHOOTER);
    private Accelerator _accelerator = new Accelerator(_wheelMotor);
    
    private final double OUTPUT_SCALE = 0.1;

    public Shooter() {
        super(0.005, 0, 0.03);
        _speedSensor.setMinSpeedRpm(200);
        _accelerator.setMinSpeed(0);
        getPIDController().setOutputRange(-1/OUTPUT_SCALE, 1/OUTPUT_SCALE);
        LiveWindow.addSensor("Shooter", "Speed Sensor", _speedSensor);
        LiveWindow.addActuator("Shooter", "PID Control", getPIDController());
    }

    public double getSpeedRpm() {
        return _speedSensor.getSpeedRpm();
    }

    public double getMotorValRaw() {
        return _wheelMotor.get();
    }

    public void setMotorValRaw(double val) {
        _wheelMotor.set(val);
    }

    protected void initDefaultCommand() {
    }

    protected double returnPIDInput() {
        return _speedSensor.getSpeedRpm();
    }

    protected void usePIDOutput(double output) {
        _accelerator.set(OUTPUT_SCALE*output);
    }

    public void setAcceleratorEnabled(boolean enabled) {
        _accelerator.setEnabled(enabled);
    }
}
