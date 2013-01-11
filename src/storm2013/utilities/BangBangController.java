/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.parsing.IUtility;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author evan1026
 */

public class BangBangController implements IUtility, LiveWindowSendable {
    
    private PIDOutput     _PIDOut;
    private IEncoderSpeed _SpeedCalculator;
    private double        _setPoint;
    private double        _spinUpPoint;
    private double        _lastSpeed;
    private double        _spinUpSpeed;
    private double        _maxSpeed;
    private Timer         _timer;
    private ITable        _table;
    
    private ITableListener _listener = new ITableListener(){
        public void valueChanged(ITable itable, String key, Object value, boolean isNew) {
            if      (key.equals("setPoint"))      setSetPoint   (_table.getNumber("setPoint"));
            else if (key.equals("spinUpPoint"))   setSpinUpPoint(_table.getNumber("spinUpPoint"));
            else if (key.equals("spinUpSpeed"))   setSpinUpSpeed(_table.getNumber("spinUpSpeed"));
            else if (key.equals("maxSpeed"))      setMaxSpeed   (_table.getNumber("maxSpeed"));
        }
    };

    public BangBangController(PIDOutput PIDOut, IEncoderSpeed SpeedCalculator, ITable table,
            double setPoint, double spinUpPoint, double spinUpSpeed, double maxSpeed, 
            double period){
        
	_PIDOut          = PIDOut;
	_SpeedCalculator = SpeedCalculator;
	_setPoint        = setPoint;
	_spinUpPoint     = spinUpPoint;
	_spinUpSpeed     = spinUpSpeed;
	_maxSpeed        = maxSpeed;
        _timer           = new Timer();
        
        _timer.schedule(new BangBangTask(this), 0L, (long)(period * 1000));
        
        //InitTable(table);
    }

    public void go() {
	double currSpeed = _SpeedCalculator.getRPM();
	if (!(currSpeed == _lastSpeed)){
	    if (currSpeed < _spinUpPoint){
                _PIDOut.pidWrite(_spinUpSpeed);
	    }
	    else if (currSpeed < _setPoint){
		_PIDOut.pidWrite(_maxSpeed);
	    }
	    else{
		_PIDOut.pidWrite(0);
	    }
	    _lastSpeed = currSpeed;
	}
    }
    
    public void initTable(ITable table){
        if(_table != null) _table.removeTableListener(_listener);
        _table = table;
        
        if (table != null){
            _table.putNumber("setPoint",    _setPoint);
            _table.putNumber("spinUpPoint", _spinUpPoint);
            _table.putNumber("spinUpSpeed", _spinUpSpeed);
            _table.putNumber("maxSpeed",    _maxSpeed);
            _table.addTableListener(_listener, false);
        }
    }

    /*Gets and sets*/
    public double getSetPoint() {
        return _setPoint;
    }
    public void setSetPoint(double setPoint) {
        _setPoint = setPoint;
        _table.putNumber("setPoint", _setPoint);
    }
    public double getSpinUpPoint() {
        return _spinUpPoint;
    }
    public void setSpinUpPoint(double spinUpPoint) {
        _spinUpPoint = spinUpPoint;
        _table.putNumber("spinUpPoint", _spinUpPoint);
    }
    public double getSpinUpSpeed() {
        return _spinUpSpeed;
    }
    public void setSpinUpSpeed(double spinUpSpeed) {
        _spinUpSpeed = spinUpSpeed;
        _table.putNumber("spinUpSpeed", _spinUpSpeed);
    }
    public double getMaxSpeed() {
        return _maxSpeed;
    }
    public void setMaxSpeed(double maxSpeed) {
        _maxSpeed = maxSpeed;
        _table.putNumber("maxSpeed", _maxSpeed);
    }
    public ITable getTable(){
        return _table;
    }
    
    public String getSmartDashboardType(){
        return "BangBangController";
    }

    public void updateTable() {
    }

    public void startLiveWindowMode() {
    }

    public void stopLiveWindowMode() {
    }
    
    private class BangBangTask extends TimerTask{

        private BangBangController _controller;
        
        public BangBangTask(BangBangController controller) {
            if (controller == null) {
                throw new NullPointerException("Given PIDController was null");
            }
            _controller = controller;
        }
        
        public void run() {
            _controller.go();
        }
    
    }
}


