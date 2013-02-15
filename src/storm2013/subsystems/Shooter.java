package storm2013.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2013.RobotMap;
import storm2013.commands.DoNothing;
import storm2013.commands.SpinDown;
import storm2013.utilities.Accelerator;
import storm2013.utilities.HallEffectSpeedSensor;

/**
 *
 * @author Storm
 */
public class Shooter extends PIDSubsystem {

    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT);
    private Victor _wheelMotor = new Victor(RobotMap.PORT_MOTOR_SHOOTER);
    private Accelerator _accelerator = new Accelerator(_wheelMotor,true);
    
    private final double OUTPUT_SCALE = 0.1;

    public Shooter() {
        super(0.025, 0, 0.28);
        _speedSensor.setMinSpeedRpm(200);
        _accelerator.setMinSpeed(0);
        getPIDController().setOutputRange(-1/OUTPUT_SCALE, 1/OUTPUT_SCALE);
        setAbsoluteTolerance(25);
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
        CommandGroup timeout = new CommandGroup("Time out shooter");
        timeout.addSequential(new DoNothing(),5);
        timeout.addSequential(new SpinDown());
        setDefaultCommand(timeout);
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

    public void enable() {
        super.enable();
        _accelerator.setEnabled(true);
    }

    public void disable() {
        super.disable();
        _accelerator.setEnabled(false);
    }
    
    private ITable _table;
    
    private ITableListener _listener = new ITableListener() {

        public void valueChanged(ITable source, String key, Object value, boolean isNew) {
            if(key.equals("enabled")) {
                setAcceleratorEnabled(((Boolean)value).booleanValue());
            }
        }
    };

    public void initTable(ITable table) {
        if(_table != null) {
            _table.removeTableListener(_listener);
        }
        super.initTable(table);
        _table = table;
        if(_table != null) {
            table.addTableListener(_listener);
        }
    }
    
    public boolean isEnabled() {
        return getPIDController().isEnable();
    }
}
