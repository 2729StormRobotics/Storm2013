package storm2013.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
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

    public Accelerator(SpeedController controller,boolean negated) {
        this(controller, DEFAULT_PERIOD);
        _negated = negated;
    }

    public Accelerator(SpeedController controller, double period) {
        if (controller == null) {
            throw new NullPointerException("Accelerator requires a non-null SpeedController");
        }
        _controller = controller;
        _period = period;
        _timer.schedule(new _BgTask(), 0, (long) (1000 * _period));
    }

    public double get() {
        return _rate;
    }

    /**
     * @deprecated
     */
    public void set(double speed, byte syncGroup) {
    }

    public void set(double speed) {
        if(_enabled) {
            _rate = speed;
        }
    }

    public void disable() {
        setEnabled(false);
    }

    public synchronized void setEnabled(boolean enabled) {
        if(!enabled) {
            set(0);
        }
        _enabled = enabled;
    }

    public void pidWrite(double output) {
        set(output);
    }

    public void setMinSpeed(double minSpeed) {
        if(!_negated) {
            _minSpeed = minSpeed;
        } else {
            _maxSpeed = minSpeed;
        }
    }

    public void setMaxSpeed(double maxSpeed) {
        if(_negated) {
            _minSpeed = maxSpeed;
        } else {
            _maxSpeed = maxSpeed;
        }
    }
}
