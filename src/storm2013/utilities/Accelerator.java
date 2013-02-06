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
            if (_enabled) {
                double newSpeed = _controller.get() + _rate * _period;
                if (newSpeed > _minSpeed && newSpeed < _maxSpeed) {
                    _controller.set(newSpeed);
                    System.out.println("New Speed: " + newSpeed + " RPM");
                }
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

    public Accelerator(SpeedController controller) {
        this(controller, DEFAULT_PERIOD);
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
        _rate = speed;
        System.out.println("New rate: " + _rate);
    }

    public void disable() {
        setEnabled(false);
    }

    public void setEnabled(boolean enabled) {
        if (_enabled == enabled) {
            return;
        }
        if (!enabled) {
            _controller.set(0);
        }
        _enabled = enabled;
    }

    public void pidWrite(double output) {
        set(output);
    }

    public void setMinSpeed(double minSpeed) {
        _minSpeed = minSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        _maxSpeed = maxSpeed;
    }
}
