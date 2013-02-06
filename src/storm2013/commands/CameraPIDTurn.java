/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.tables.ITable;
import storm2013.Robot;
import storm2013.utilities.CameraPIDSource;

/**
 *
 * @author evan1026
 */
public class CameraPIDTurn extends PIDCommand {
    
    private PIDController _controller;
    private CameraPIDSource _source;
    
    private ITable _table;
    private String _targetFoundKey;
    private Timer _timeyWimey = new Timer();
    private double _timeout;
    private boolean _willTimeout;
    
    
    public CameraPIDTurn(ITable table, CameraPIDSource.Target target, double timeout){
        super(0, 0, 0);
        requires(Robot.driveTrain);
        _source = new CameraPIDSource(table, target);
        _controller = getPIDController();
        _controller.setAbsoluteTolerance(0.1);
        _controller.setOutputRange(-0.6, 0.6);
        _table = table;
        _timeout = timeout;
        if (timeout != -1) _willTimeout = true;
        _targetFoundKey = target.getFoundKey();
    }
    
    protected double returnPIDInput() {
        return _source.pidGet();
    }

    protected void usePIDOutput(double output) {
        System.out.println("Driving with " + output);
        Robot.driveTrain.tankDrive(-output, output);
    }

    protected void initialize() {
        _controller.setSetpoint(0);
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
}
