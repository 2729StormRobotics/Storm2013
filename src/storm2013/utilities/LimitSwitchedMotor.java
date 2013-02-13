package storm2013.utilities;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.buttons.Trigger;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author ginto
 */
public class LimitSwitchedMotor implements SpeedController {
    private static final double DEFAULT_PERIOD = 1.0/50;
    
    private final SpeedController _controller;
    private final Trigger _top,_bottom;
    private final boolean      _topOn,_bottomOn;
    private double _value;
    private Timer _bgThread = new Timer();
    
    private TimerTask _bgTask = new TimerTask() {
        public void run() {
            double output = _value;
            if(output > 0 && _top != null) {
                if(_top.get() == _topOn) {
                    output = 0;
                }
            } else if(output < 0 && _bottom != null) {
                if(_bottom.get() == _bottomOn) {
                    output = 0;
                }
            }
            _controller.set(output);
        }
    };

    // "top" being positive, "bottom" negative
    public LimitSwitchedMotor(SpeedController controller,Trigger top,boolean topOnValue,
                                                         Trigger bottom,boolean bottomOnValue) {
        _controller = controller;
        _top = top;
        _bottom = bottom;
        _bgThread.schedule(_bgTask, 0, (long)(DEFAULT_PERIOD*1000));
        _topOn = topOnValue;
        _bottomOn = bottomOnValue;
    }

    public double get() {
        return _value;
    }

    public void set(double speed, byte syncGroup) {
        set(speed);
    }

    public void set(double speed) {
        _value = speed;
    }

    public void disable() {
        set(0);
        _controller.disable();
    }

    public void pidWrite(double output) {
        set(output);
    }
    
}
