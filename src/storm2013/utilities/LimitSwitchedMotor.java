package storm2013.utilities;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.buttons.Trigger;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Uses two triggers (of any type) to keep a motor from spinning outside of a certain range.
 * @author ginto
 */
public class LimitSwitchedMotor implements SpeedController {
    private static final double DEFAULT_PERIOD = 1.0/50;
    
    private final SpeedController _controller;
    private final Trigger _top,_bottom;
    private final boolean      _topOn,_bottomOn;
    private double _value;
    private Timer _bgThread = new Timer();
    private boolean _limitingEnabled = true;
    private Timer _safetyOnTimer = new Timer();
    
    private TimerTask _bgTask = new TimerTask() {
        public void run() {
            double output = _value;
            if(_limitingEnabled) {
                if(output > 0 && _top != null) {
                    if(_top.get() == _topOn) {
                        output = 0;
                    }
                } else if(output < 0 && _bottom != null) {
                    if(_bottom.get() == _bottomOn) {
                        output = 0;
                    }
                }
            }
            _controller.set(output);
        }
    };
    
    private TimerTask _resetSafety = new TimerTask() {
        public void run() {
            _limitingEnabled = true;
        }
    };

    // "top" being positive, "bottom" negative
    /**
     * 
     * @param controller The {@link SpeedController} that controls the motor.
     * @param top The top {@link Trigger} (trigger at the positive end of the motor's output).
     * @param topOnValue The value from the top {@link Trigger} where you want the motor to stop.
     * @param bottom The bottom {@link Trigger} (trigger at the negative end of the motor's output).
     * @param bottomOnValue The value from the bottom {@link Trigger} where you want the motor to stop.
     */
    public LimitSwitchedMotor(SpeedController controller,Trigger top,boolean topOnValue,
                                                         Trigger bottom,boolean bottomOnValue) {
        _controller = controller;
        _top = top;
        _bottom = bottom;
        _bgThread.schedule(_bgTask, 0, (long)(DEFAULT_PERIOD*1000));
        _topOn = topOnValue;
        _bottomOn = bottomOnValue;
    }

    /**
     * Gets the speed at which the motor is running.
     * @return The current output speed
     */
    public double get() {
        return _value;
    }

    /**
     * Sets a new output speed.
     * @param speed The new speed
     * @param syncGroup Honestly, I have no idea, but it's not used anyway
     */
    public void set(double speed, byte syncGroup) {
        set(speed);
    }

    /**
     * Sets the new output speed
     * @param speed The new speed
     */
    public void set(double speed) {
        _value = speed;
    }

    /**
     * Stops the motor and disables its controller
     */
    public void disable() {
        set(0);
        _controller.disable();
    }

    /**
     * Writes values from the PID controller to the motor.
     * @param output 
     */
    public void pidWrite(double output) {
        set(output);
    }
    
    /**
     * Disables limiting temporarily. If this is not called for 1/10 of a second,
     * limiting is re-enabled.
     */
    public void disableSafety() {
        _safetyOnTimer.cancel();
        _limitingEnabled = false;
        _safetyOnTimer.schedule(_resetSafety, (long)(0.1*1000));
    }
}
