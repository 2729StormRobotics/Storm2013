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
 * Subsystem including the shooter wheel and the Hall Effect sensor. It's a
 * {@link PIDSubsystem} so it includes a PID feedback controller.
 */
public class Shooter extends PIDSubsystem {
    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT);
    private Jaguar _wheelMotor = new Jaguar(RobotMap.PORT_MOTOR_SHOOTER);
    // This serves as the output of the PID loop, because PID works best when
    // controlling an output proportional to the first derivative of the speed
    // sensor.
    private Accelerator _accelerator = new Accelerator(_wheelMotor,true);
    
    private final double OUTPUT_SCALE = 0.1;

    public Shooter() {
        super(0.08, 0, 0.28);
        _speedSensor.setMinSpeedRpm(200);
        _accelerator.setMinSpeed(0);
        getPIDController().setOutputRange(-2/OUTPUT_SCALE, 2/OUTPUT_SCALE);
        setAbsoluteTolerance(25);
        LiveWindow.addSensor("Shooter", "Speed Sensor", _speedSensor);
        LiveWindow.addActuator("Shooter", "PID Control", getPIDController());
    }

    /**  Reads the current wheel speed in RPM. */
    public double getSpeedRpm() {
        return _speedSensor.getSpeedRpm();
    }

    /** Reads the current signal being output to the wheel's speed controller. */
    public double getMotorValRaw() {
        return _wheelMotor.get();
    }

    /** Directly sets the signal to the wheel's speed controller. */
    public void setMotorValRaw(double val) {
        _wheelMotor.set(val);
    }

    protected void initDefaultCommand() {
        // This command makes the shooter automatically time out if no command
        // uses it for 5 seconds
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

    /** Enables or disables the {@link Accelerator}. */
    public void setAcceleratorEnabled(boolean enabled) {
        _accelerator.setEnabled(enabled);
    }

    /** Enables both the PID controller and the {@link Accelerator}. */
    public void enable() {
        super.enable();
        _accelerator.setEnabled(true);
    }

    /** Disables both the subsystem and the {@link Accelerator}. */
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

    /** Initializes this subsystem to listen to a network table for values. */
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
    
    /** Returns whether or not the subsystem is enabled. */
    public boolean isEnabled() {
        return getPIDController().isEnable();
    }
}
