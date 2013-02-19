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
    public static final double  SPEED_DEFAULT = 1;
    private static final double UP_SIGN       = -1;
    
    private final Jaguar _motor = new Jaguar(RobotMap.PORT_MOTOR_TILTER);
    private final DigitalInput _topLimitSwitch = new DigitalInput(RobotMap.PORT_LIMIT_TILTER_TOP);
    private final boolean _topLimitOnState = true;
    private Trigger _topLimitTrigger    = new Trigger() {
                                             public boolean get() {
                                                 return _topLimitSwitch.get();
                                             }
                                         },
                    _bottomLimitTrigger = null;
    private LimitSwitchedMotor _limitedMotor = new LimitSwitchedMotor(_motor,
                                                                      _bottomLimitTrigger, true,
                                                                      _topLimitTrigger,    _topLimitOnState);

    /**
     * Constructs the tilter and adds it to {@link LiveWindow}
     */
    public Tilter() {
        LiveWindow.addActuator("Tilter", "Motor", _motor);
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
        move(SPEED_DEFAULT);
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
        move(-SPEED_DEFAULT);
    }
    
    public boolean isTopLimitTriggered() {
        return _topLimitSwitch.get() == _topLimitOnState;
    }
}
