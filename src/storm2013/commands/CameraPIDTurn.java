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
public class CameraPIDTurn extends PIDCommand implements ITableListener {
    
    private PIDController _controller;
    
    private ITable _table;
    private String _targetFoundKey;
    private String _targetKey;
    private Timer _timeyWimey = new Timer();
    private double _timeout;
    private boolean _willTimeout;
    
    public CameraPIDTurn(ITable table, Target target, double timeout){
        super(0.5, 0.1, 1);
        requires(Robot.driveTrain);
        _controller = getPIDController();
        _controller.setAbsoluteTolerance(5);
        _controller.setOutputRange(-6, 6);
        _table = table;
        _timeout = timeout;
        if (timeout != -1) _willTimeout = true;
        _targetFoundKey = target.getFoundKey();
        _targetKey = target.getXKey();
    }
    
    protected double returnPIDInput() {
        return Robot.driveTrain.getGyroAngle();
    }

    protected void usePIDOutput(double output) {
        output /= 10;
        System.out.println("Driving with " + output);
        if(!_controller.onTarget()) {
            Robot.driveTrain.tankDrive(output, -output);
        } else {
            Robot.driveTrain.tankDrive(0, 0);
        }
    }

    protected void initialize() {
        Robot.driveTrain.clearGyro();
        _controller.setSetpoint(_table.getNumber(_targetKey));
        _table.addTableListener(this);
        _controller.enable();
        _timeyWimey.start();
    }

    protected void execute() {
        if (_willTimeout){
            if(_table.getBoolean(_targetFoundKey)){
                _timeyWimey.reset();
                _controller.enable();
                System.out.println("Found");
            }
            if(_timeyWimey.get() > _timeout) _controller.disable();
        }
    }

    protected boolean isFinished() {
        return false;
//        return _controller.onTarget();
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
            Robot.driveTrain.clearGyro();
            _controller.setSetpoint(_table.getNumber(_targetKey));
        }
    }
}
