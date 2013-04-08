package storm2013.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.RobotMap;
import storm2013.commands.MoveTilter;
import storm2013.utilities.LimitSwitchedMotor;
import storm2013.utilities.StringPot;

/** Subsystem including the tilter and its associated hardware. */
public class Tilter extends Subsystem {
    public static final double  SPEED_DEFAULT = 1;
    private static final double UP_SIGN       = -1;
    
    private final Jaguar _motor = new Jaguar(RobotMap.PORT_MOTOR_TILTER);
    private final StringPot _stringPot = new StringPot(RobotMap.PORT_STRINGPOT);
    private final DigitalInput _topLimitSwitch = new DigitalInput(RobotMap.PORT_LIMIT_TILTER_TOP);
    private final boolean _topLimitOnState = true;
    private Trigger _topLimitTrigger    = new Trigger() {
                                             public boolean get() {
                                                 return isTopLimitTriggered();
                                             }
                                         },
                    _bottomLimitTrigger = null;
    private LimitSwitchedMotor _limitedMotor = new LimitSwitchedMotor(_motor,
                                                                      _bottomLimitTrigger, true,
                                                                      _topLimitTrigger,    _topLimitOnState);

    public Tilter() {
        LiveWindow.addActuator("Tilter", "Motor", _motor);
        LiveWindow.addSensor("Tilter", "String pot", _stringPot);
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new MoveTilter());
    }
    
    /** Moves the tilter at a given speed (+ = up,- = down). */
    public void move(double speed) {
        _limitedMotor.set(UP_SIGN*speed);
    }
    
    /** Moves the tilter up. */
    public void moveUp() {
        move(SPEED_DEFAULT);
    }
    
    /** Stops the tilter. */
    public void stop() {
        move(0);
    }
    
    /** Moves the tilter down. */
    public void moveDown() {
        move(-SPEED_DEFAULT);
    }
    
    /** Reads whether the tilter is at/above its highest safe height. */
    public boolean isTopLimitTriggered() {
        return _stringPot.get() > StringPot.VAL_MIN_UNSAFE && _topLimitSwitch.get() == _topLimitOnState;
    }
    
    public double readStringPot() {
        return _stringPot.get();
    }
}
