package storm2013.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import storm2013.RobotMap;
import storm2013.commands.MoveTilter;
import storm2013.utilities.LimitSwitchedMotor;

/**
 * Provides functionality to interface with the tilter.
 * @author Joe
 */
public class Tilter extends Subsystem {
    public static final double  SPEED_DEFAULT = 0.6;
    private static final double UP_SIGN       = -1;
    
    private static final double ANGLE_MAX = 90, // TODO: find actual values for these.
                                ANGLE_MIN = 0;
    
    private Jaguar _motor = new Jaguar(RobotMap.PORT_MOTOR_TILTER);
    private Trigger _topLimitSwitch    = new Trigger() {
                                             private DigitalInput _limit = new DigitalInput(RobotMap.PORT_LIMIT_TILTER_TOP);

                                             public boolean get() {
                                                 return _limit.get();
                                             }
                                         },
                    _bottomLimitSwitch = null;
    private LimitSwitchedMotor _limitedMotor = new LimitSwitchedMotor(_motor,
                                                                      _bottomLimitSwitch, true,
                                                                      _topLimitSwitch,    true);
    private ADXL345_I2C _accelerometer = new ADXL345_I2C(RobotMap.MODULE_SENSOR_ACCELEROMETER,
                                                         ADXL345_I2C.DataFormat_Range.k2G);
    private LiveWindowSendable _angleSensor = new LiveWindowSendable() {
        private ITable _table;
        
        public void updateTable() {
            if(_table != null) {
                _table.putNumber("Value", getAngle());
            }
        }

        public void startLiveWindowMode() {}

        public void stopLiveWindowMode() {}

        public void initTable(ITable table) {
            _table = table;
            updateTable();
        }

        public ITable getTable() {
            return _table;
        }

        public String getSmartDashboardType() {
            return "Gyro";
        }
    };

    /**
     * Constructs the tilter and adds it to {@link LiveWindow}
     */
    public Tilter() {
        LiveWindow.addActuator("Tilter", "Motor", _motor);
        LiveWindow.addSensor("Tilter", "Angle", _angleSensor);
    }

    /**
     * Sets the default command to be {@link MoveTilter}
     */
    public void initDefaultCommand() {
        setDefaultCommand(new MoveTilter());
    }
    
    /**
     * Moves the tilter up at a certain speed (use negative values to move down,
     * or just use {@link #moveUp()} and {@link #moveDown()}.
     * @param speed 
     */
    public void move(double speed) {
        _limitedMotor.set(UP_SIGN*speed);
    }
    
    /**
     * Moves the tilter up.
     */
    public void moveUp() {
        move(UP_SIGN*SPEED_DEFAULT);
    }
    
    /**
     * Stops the tilter.
     */
    public void stop() {
        move(0);
    }
    
    /**
     * Moves the tilter down
     */
    public void moveDown() {
        move(-UP_SIGN*SPEED_DEFAULT);
    }
    
    private double _lerped = 0;
    private static final double LERP_CONST = 0.3;
    
    /**
     * Returns the angle of the tilter based on accelerometer values. It also lerps
     * the values to help reduce noise.
     * @return the angle
     */
    public double getAngle() {
        double x = _accelerometer.getAcceleration(ADXL345_I2C.Axes.kX),
               y = _accelerometer.getAcceleration(ADXL345_I2C.Axes.kY), // g*sin(theta)
               z = _accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ);
        SmartDashboard.putNumber("Accel X", x);
        SmartDashboard.putNumber("Accel Y", y);
        SmartDashboard.putNumber("Accel Z", z);
        // assuming y axis is forward-back
        _lerped = _lerped*(1-LERP_CONST) + LERP_CONST*Math.abs(Math.toDegrees(MathUtils.asin(y)));
        return _lerped;
    }
}
