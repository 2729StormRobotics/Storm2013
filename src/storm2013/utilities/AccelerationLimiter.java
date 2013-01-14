/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Joe
 */
public class AccelerationLimiter implements SpeedController,LiveWindowSendable {
    private SpeedController _controller;
    private double _maxAccel,_maxDecel;
    private double _target = 0;
    private double _period;
    private java.util.Timer _bgThread;
    
    private class LimiterTask extends TimerTask {

        public void run() {
            double current = _controller.get();
            
            if(current == _target) {
                return;
            }
            
            double rate;
            
            if(_target > current && current >= 0) {
                rate = _maxAccel;
            } else if(_target < current && current <= 0) {
                rate = -_maxAccel;
            } else if(current < 0) {
                rate = _maxDecel;
            } else {
                rate = -_maxDecel;
            }
            
            double newSpeed = current+rate*_period;
            if((current < _target && newSpeed > _target) ||
               (current > _target && newSpeed < _target)) {
                newSpeed = _target;
            }
            
            _controller.set(newSpeed);
        }
        
    }
    
    /**
     * 
     * @param controller
     * @param maxAccel
     * @param maxDecel
     * @param period 
     */
    public AccelerationLimiter(SpeedController controller,double maxAccel,double maxDecel,double period) {
        if(controller == null) {
            throw new NullPointerException("AccelerationLimiter must be given a non-null SpeedController");
        }
        _controller = controller;
        _maxAccel   = maxAccel;
        _maxDecel   = maxDecel;
        _period     = period;
        _bgThread   = new java.util.Timer();
        _bgThread.schedule(new LimiterTask(), 0, (long)(_period*1000));
    }
    public AccelerationLimiter(SpeedController controller,double maxAccel,double maxDecel) {
        // Default to 1/20 second period
        this(controller,maxAccel,maxDecel,1.0/20);
    }
    
    public void setMaxAccel(double maxAccel) {
        _maxAccel = maxAccel;
    }
    
    public double getMaxAccel() {
        return _maxAccel;
    }
    
    public void setMaxDecel(double maxDecel) {
        _maxDecel = maxDecel;
    }
    
    public double getMaxDecel() {
        return _maxDecel;
    }

    public double get() {
        return _target;
    }

    /** @deprecated - this is only for CAN Jaguar apparently
     */
    public void set(double speed, byte syncGroup) {
        set(speed);
    }

    public void set(double speed) {
        _target = speed;
    }

    public void disable() {
        _controller.disable();
    }

    /**
     * @deprecated - If you're using this with PID, you have problems
     */
    public void pidWrite(double output) {
        set(output);
    }
    
    ITable _table;
    
    private ITableListener listener = new ITableListener() {
        public void valueChanged(ITable table, String key, Object value, boolean isNew) {
            if(key.equals("Max Acceleration")) {
                setMaxAccel(((Double)value).doubleValue());
            } else if(key.equals("Max Deceleration")) {
                setMaxDecel(((Double)value).doubleValue());
            } else if(key.equals("Target")) {
                set(((Double)value).doubleValue());
            }
        }
    };
    
    public void initTable(ITable table) {
        if(_table!=null)
            _table.removeTableListener(listener);
        _table = table;
        if(_table!=null){
            _table.putNumber ("Max Acceleration", _maxAccel);
            _table.putNumber ("Max Deceleration", _maxDecel);
            _table.putNumber ("Target",           _target);
            _table.addTableListener(listener, false);
        }
        updateTable();
    }

    public ITable getTable() {
        return _table;
    }
    
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Current Speed", _controller.get());
        }
    }

    public void startLiveWindowMode() {
        set(0.0);
    }

    public void stopLiveWindowMode() {
        set(0.0);
    }

    public String getSmartDashboardType() {
        return "Acceleration Limiter";
    }
}
