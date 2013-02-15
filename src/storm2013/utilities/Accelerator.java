package storm2013.utilities;

import edu.wpi.first.wpilibj.SpeedController;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Accelerates a motor (specified by the speed controller) up to the output speed.
 * It also makes sure the output value does not go outside the range set by the 
 * minimum and maximum speed.
 * @author Joe
 */
public class Accelerator implements SpeedController {

    private class _BgTask extends TimerTask {

        public void run() {
            double sign = _negated ? -1 : 1;
            double newSpeed = _controller.get() + sign*_rate * _period;
            if (newSpeed < _minSpeed) {
                _controller.set(_minSpeed);
            } else if(newSpeed > _maxSpeed) {
                _controller.set(_maxSpeed);
            } else {
                _controller.set(newSpeed);
            }
        }
    }
    private static final double DEFAULT_PERIOD = 1.0 / 50;
    
    SpeedController _controller;
    double _rate = 0;
    double _period;
    Timer _timer = new Timer();
    boolean _enabled = true;
    double _minSpeed = -1, _maxSpeed = 1;
    boolean _negated = false;

    /**
     * It's a constructor.
     * @param controller The {@link SpeedController} to use as output.
     * @param negated True if the motor values should be negated.
     */
    public Accelerator(SpeedController controller,boolean negated) {
        this(controller, DEFAULT_PERIOD);
        _negated = negated;
    }

    /**
     * Also a constructor
     * @param controller The {@link SpeedController} to use as output.
     * @param period How often it should be updated.
     */
    public Accelerator(SpeedController controller, double period) {
        if (controller == null) {
            throw new NullPointerException("Accelerator requires a non-null SpeedController");
        }
        _controller = controller;
        _period = period;
        _timer.schedule(new _BgTask(), 0, (long) (1000 * _period));
    }

    /**
     * Gets the speed at which the motor should accelerate to
     * @return the aforementioned rate
     */
    public double get() {
        return _rate;
    }

    /**
     * Doesn't do anything. Use {@link #set(double)}
     * @deprecated 
     */
    public void set(double speed, byte syncGroup) {
    }

    /**
     * Sets the new speed
     * @param speed the new speed
     */
    public void set(double speed) {
        if(_enabled) {
            _rate = speed;
        }
    }

    /**
     * Disables the controller.
     */
    public void disable() {
        setEnabled(false);
    }

    /**
     * Enables/disables the controller
     * @param enabled True to enable, false to disable.
     */
    public synchronized void setEnabled(boolean enabled) {
        if(!enabled) {
            set(0);
        }
        _enabled = enabled;
    }

    /**
     * Writes the output from the PID controller to {@link #set(double)}
     * @param output 
     */
    public void pidWrite(double output) {
        set(output);
    }

    /**
     * Sets the minimum speed
     * @param minSpeed the minimum speed
     */
    public void setMinSpeed(double minSpeed) {
        if(!_negated) {
            _minSpeed = minSpeed;
        } else {
            _maxSpeed = minSpeed;
        }
    }

    /**
     * Sets the maximum speed
     * @param maxSpeed The maximum speed
     */
    public void setMaxSpeed(double maxSpeed) {
        if(_negated) {
            _minSpeed = maxSpeed;
        } else {
            _maxSpeed = maxSpeed;
        }
    }
}
