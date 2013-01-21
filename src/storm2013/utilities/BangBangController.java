/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.parsing.IUtility;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Timer;
import java.util.TimerTask;


/**
 * The BangBangController has three parts: When the speed of the motor (found through
 * the {@link IEncoderSpeed} interface which just gives the controller the motor speed
 * in RPM) is less than {@link #_spinUpPoint}, the {@link PIDOutput} will be set to
 * {@link #_spinUpSpeed}. When the speed gets up to {@link #_spinUpPoint} but is still
 * less than {@link #_setPoint}, the output will be set to {@link #_maxSpeed}. Once the
 * speed reaches the {@link #_setPoint}, the output is set to zero, allowing the motor to
 * simply coast. This is done to conserve energy and to reduce strain on the motors.
 * 
 * @author evan1026
 */

public class BangBangController implements IUtility, LiveWindowSendable {
    
    private PIDOutput     _PIDOut;
    private ISpeedSensor _SpeedCalculator;
    private double        _setPoint;
    private double        _spinUpPoint;
    private double        _lastSpeed;
    private double        _spinUpSpeed;
    private double        _maxSpeed;
    private Timer         _timer;
    private ITable        _table;
    
    private ITableListener _listener = new ITableListener(){
        public void valueChanged(ITable itable, String key, Object value, boolean isNew) {
            if (key.equals("setPoint")) {
                setSetPoint(_table.getNumber("setPoint"));
            }
            else if (key.equals("spinUpPoint")) {
                setSpinUpPoint(_table.getNumber("spinUpPoint"));
            }
            else if (key.equals("spinUpSpeed")) {
                setSpinUpSpeed(_table.getNumber("spinUpSpeed"));
            }
            else if (key.equals("maxSpeed")) {
                setMaxSpeed   (_table.getNumber("maxSpeed"));
            }
        }
    };

    /**
     * The default (and only, at the moment) constructor.
     * 
     * @param PIDOut           The {@link PIDOutput} that the controller outputs to.
     * @param SpeedCalculator  The class that implements {@link IEncoderSpeed} so that this controller can get the speed of the motor.
     * @param table            The {@link ITable} that the controller should report its values to and retrieve values from.
     * @param setPoint         The speed (RPM) that you want the motor to go.
     * @param spinUpPoint      The point at which the motor switches from the spin up speed to the max speed.
     * @param spinUpSpeed      The speed the motor should spin at (RPM) while on its way to the spin up point.
     * @param maxSpeed         The speed the motor should spin at (RPM) once it's passed the spin up point but hasn't yet reached the set point.
     * @param period           The period between executions of {@link #go()}. (If you don't know what to make it, go for about 50, which makes it run 20 times a second).
     * @see PIDOutput
     * @see IEncoderSpeed
     * @see ITable
     */
    public BangBangController(PIDOutput PIDOut, ISpeedSensor SpeedCalculator,
            double setPoint, double spinUpPoint, double spinUpSpeed, double maxSpeed, 
            double period){
        
        if (PIDOut == null){
            throw new NullPointerException("The PID output cannot be null.");
        }
        if (SpeedCalculator == null){
            throw new NullPointerException("The speed calculator cannot be null.");
        }
        
        _PIDOut          = PIDOut;
        _SpeedCalculator = SpeedCalculator;
        _setPoint        = setPoint;
        _spinUpPoint     = spinUpPoint;
        _spinUpSpeed     = spinUpSpeed;
        _maxSpeed        = maxSpeed;
        _timer           = new Timer();
        
        _timer.schedule(new BangBangTask(this), 0L, (long)(period * 1000));
    }

    /**
     * Does the calculations and sets the output. It would be really weird if you called
     * this yourself, let the {@link Timer} do it for you.
     */
    public void go() {
        double currSpeed = _SpeedCalculator.getSpeedRpm();
	if (Math.abs(currSpeed) < Math.abs(_spinUpPoint)){
	    _PIDOut.pidWrite(_spinUpSpeed);
	}
	else if (Math.abs(currSpeed) < Math.abs(_setPoint)){
	    _PIDOut.pidWrite(_maxSpeed);
	}
	else{
	    _PIDOut.pidWrite(0);
	}
    }
    
    /**
     * SmartDashboard wants it, so SmartDashboard gets it. Basically this just ends up
     * initializing the table values associated with this controller. Don't call it
     * yourself. That's just weird.
     * 
     * @param table The table that the controller should output its values to.
     */
    public void initTable(ITable table){
        if(_table != null) {
            _table.removeTableListener(_listener);
        }
        _table = table;
        
        if (table != null){
            _table.putNumber("setPoint",    _setPoint);
            _table.putNumber("spinUpPoint", _spinUpPoint);
            _table.putNumber("spinUpSpeed", _spinUpSpeed);
            _table.putNumber("maxSpeed",    _maxSpeed);
            _table.addTableListener(_listener, false);
        }
    }

    /**
     * Gets the current set point.
     * 
     * @return The set point.
     */
    public double getSetPoint() {
        return _setPoint;
    }
    
    /**
     * Sets a new set point.
     * 
     * @param setPoint The new set point.
     */
    public void setSetPoint(double setPoint) {
        _setPoint = setPoint;
	if(_table != null) {
	    _table.putNumber("setPoint", _setPoint);
	}
    }
    
    /**
     * Gets the current spin up point.
     * 
     * @return The current spin up point.
     */
    public double getSpinUpPoint() {
        return _spinUpPoint;
    }
    
    /**
     * Sets a new spin up point.
     * 
     * @param spinUpPoint The new spin up point.
     */
    public void setSpinUpPoint(double spinUpPoint) {
        _spinUpPoint = spinUpPoint;
	if(_table != null) {
	    _table.putNumber("spinUpPoint", _spinUpPoint);
	}
    }
    
    /**
     * Gets the current spin up speed.
     * 
     * @return The current spin up speed.
     */
    public double getSpinUpSpeed() {
        return _spinUpSpeed;
    }
    
    /**
     * Sets a new spin up speed.
     * 
     * @param spinUpSpeed The new spin up speed.
     */
    public void setSpinUpSpeed(double spinUpSpeed) {
        _spinUpSpeed = spinUpSpeed;
	
	if(_table != null) {
	    _table.putNumber("spinUpSpeed", _spinUpSpeed);
	}
    }
    
    /**
     * Gets the current maximum speed.
     * 
     * @return The current maximum speed.
     */
    public double getMaxSpeed() {
        return _maxSpeed;
    }
    
    /**
     * Sets a new maximum speed.
     * 
     * @param maxSpeed The new maximum speed.
     */
    public void setMaxSpeed(double maxSpeed) {
        _maxSpeed = maxSpeed;
	
	if(_table != null) {
	    _table.putNumber("maxSpeed", _maxSpeed);
	}
    }
    
    /**
     * {@inheritDoc}
     */
    public ITable getTable(){
        return _table;
    }
    
    /**
     * {@inheritDoc}
     */
    public String getSmartDashboardType(){
        return "BangBangController";
    }

    /**
     * {@inheritDoc}
     */
    public void updateTable() {}

    /**
     * {@inheritDoc}
     */
    public void startLiveWindowMode() {}

    /**
     * {@inheritDoc}
     */
    public void stopLiveWindowMode() {}
    
    private class BangBangTask extends TimerTask{

        private BangBangController _controller;
        
        public BangBangTask(BangBangController controller) {
            if (controller == null) {
                throw new NullPointerException("The BangBangController cannot be null.");
            }
            _controller = controller;
        }
        
        public void run() {
            _controller.go();
        }
    
    }
}


