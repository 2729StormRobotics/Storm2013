package storm2013.subsystems;

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
 * A subsystem with various methods for controlling the shooter.
 * @author Storm
 */
public class Shooter extends PIDSubsystem {

    private HallEffectSpeedSensor _speedSensor = new HallEffectSpeedSensor(RobotMap.PORT_SENSOR_HALL_EFFECT);
    private Victor _wheelMotor = new Victor(RobotMap.PORT_MOTOR_SHOOTER);
    private Accelerator _accelerator = new Accelerator(_wheelMotor,true);
    
    private final double OUTPUT_SCALE = 0.1;

    /**
     * Constructs the subsystem, sets the needed values, and adds the shooter to
     * the {@link LiveWindow}
     */
    public Shooter() {
        super(0.025, 0, 0.28);
        _speedSensor.setMinSpeedRpm(200);
        _accelerator.setMinSpeed(0);
        getPIDController().setOutputRange(-1/OUTPUT_SCALE, 1/OUTPUT_SCALE);
        setAbsoluteTolerance(25);
        LiveWindow.addSensor("Shooter", "Speed Sensor", _speedSensor);
        LiveWindow.addActuator("Shooter", "PID Control", getPIDController());
    }

    /**
     * Gets the current speed of the shooter wheel in RPM
     * @return the speed
     */
    public double getSpeedRpm() {
        return _speedSensor.getSpeedRpm();
    }

    /**
     * Gets the raw value of the shooter motor
     * @return the value
     */
    public double getMotorValRaw() {
        return _wheelMotor.get();
    }

    /**
     * Sets the raw value of the shooter motor
     * @param val The new value
     */
    public void setMotorValRaw(double val) {
        _wheelMotor.set(val);
    }

    /**
     * Sets the default command to be to wait 5 seconds, then spin the motor down.
     * This is called when all other commands using this subsystem have finished.
     */
    protected void initDefaultCommand() {
        CommandGroup timeout = new CommandGroup("Time out shooter");
        timeout.addSequential(new DoNothing(),5);
        timeout.addSequential(new SpinDown());
        setDefaultCommand(timeout);
    }

    /**
     * Returns the RPM for use in the PID controller
     * @return the current speed
     */
    protected double returnPIDInput() {
        return _speedSensor.getSpeedRpm();
    }

    /**
     * Uses the PID output to drive the motor
     * @param output the output to use
     */
    protected void usePIDOutput(double output) {
        _accelerator.set(OUTPUT_SCALE*output);
    }

    /**
     * Enables or disables the {@link Accelerator}
     * @param enabled True to enable or false to disable.
     */
    public void setAcceleratorEnabled(boolean enabled) {
        _accelerator.setEnabled(enabled);
    }

    /**
     * Enables both the subsystem and the {@link Accelerator}.
     */
    public void enable() {
        super.enable();
        _accelerator.setEnabled(true);
    }

    /**
     * Disables both the subsystem and the {@link Accelerator}
     */
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

    /**
     * Initializes this subsystem to listen to a network table for values.
     * @param table The table the subsystem should monitor
     */
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
    
    /**
     * Returns whether or not the subsystem is enabled
     * @return whether of not the subsystem is enabled
     */
    public boolean isEnabled() {
        return getPIDController().isEnable();
    }
}
