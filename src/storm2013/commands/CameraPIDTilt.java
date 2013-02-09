/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2013.Robot;
import storm2013.utilities.Target;

/**
 *
 * @author evan1026
 */
public class CameraPIDTilt extends PIDCommand implements ITableListener {
    
    private PIDController _controller;
    
    private ITable _table;
    private String _targetFoundKey;
    private String _targetKey;
    private Timer _timeyWimey = new Timer();
    private double _timeout;
    private boolean _willTimeout;
    private boolean _continuous;
    
    public CameraPIDTilt(ITable table, Target target, double timeout, boolean continuous){
        super(0, 0, 0);
        requires(Robot.tilter);
        _controller = getPIDController();
        _controller.setAbsoluteTolerance(1);
        _controller.setOutputRange(-10, 10);
        _table = table;
        _timeout = timeout;
        if (timeout != -1) _willTimeout = true;
        _targetFoundKey = target.getFoundKey();
        _targetKey = target.getXKey();
        _continuous = continuous;
    }
    
    protected double returnPIDInput() {
        return Robot.tilter.getAngle();
    }

    protected void usePIDOutput(double output) {
        output /= 10;
        if(!_controller.onTarget()) {
            Robot.tilter.move(output);
        } else {
            Robot.tilter.stop();
        }
    }

    protected void initialize() {
        _controller.setSetpoint(Robot.tilter.getAngle()+_table.getNumber(_targetKey));
        _table.addTableListener(this);
        _controller.enable();
        _timeyWimey.start();
    }

    protected void execute() {
        if (_willTimeout){
            if(_table.getBoolean(_targetFoundKey)){
                _timeyWimey.reset();
                _controller.enable();
            }
            if(_timeyWimey.get() > _timeout) _controller.disable();
        }
    }

    protected boolean isFinished() {
        return !_continuous && (!_controller.isEnable() || _controller.onTarget());
    }

    protected void end() {}

    protected void interrupted() {
        end();
    }

    public PIDController getPIDController() {
        return super.getPIDController();
    }
    
    public void valueChanged(ITable source, String key, Object value, boolean isNew) {
        if (key.equals(_targetKey)){
            _controller.setSetpoint(Robot.tilter.getAngle()+_table.getNumber(_targetKey));
        }
    }
}
