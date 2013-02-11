package storm2013.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2013.Robot;
import storm2013.utilities.Target;

/**
 *
 * @author ginto
 */
public abstract class TargetPIDCommand extends PIDCommand {
    
    private PIDController _controller;
    
    private String _targetFoundKey;
    private String _targetKey;
    private Timer _timeyWimey = new Timer();
    private double _timeout;
    private boolean _willTimeout;
    private boolean _continuous;
    
    public TargetPIDCommand(Target target, Target.Axis axis, double timeout,boolean continuous,
                            double p,double i,double d,double tolerance,double maxOutput){
        super(p,i,d);
        requires(Robot.tilter);
        _controller = getPIDController();
        _timeout = timeout;
        if (timeout >= 0) {
            _willTimeout = true;
        }
        _targetFoundKey = target.getFoundKey();
        _targetKey = target.getAxisKey(axis);
        _continuous = continuous;
        _controller.setAbsoluteTolerance(tolerance);
        _controller.setOutputRange(-maxOutput, maxOutput);
    }

    protected void usePIDOutput(double output) {
        writePIDOut(_controller.onTarget() ? 0 : output);
    }

    protected void initialize() {
        setSetpoint(computeSetpoint(SmartDashboard.getNumber(_targetKey)));
        NetworkTable.getTable("SmartDashboard").addTableListener(_listener);
        _controller.enable();
        _timeyWimey.start();
    }

    protected void execute() {
        if (_willTimeout){
            if(SmartDashboard.getBoolean(_targetFoundKey)){
                _timeyWimey.reset();
                _controller.enable();
            }
            if(_timeyWimey.get() > _timeout) {
                _controller.disable();
            }
        }
    }

    protected boolean isFinished() {
        return !_continuous && (!_controller.isEnable() || _controller.onTarget());
    }

    protected void end() {
        NetworkTable.getTable("SmartDashboard").removeTableListener(_listener);
    }

    protected void interrupted() {
        end();
    }

    public PIDController getPIDController() {
        return _controller;
    }
    
    private ITableListener _listener = new ITableListener() {
        public void valueChanged(ITable source, String key, Object value, boolean isNew) {
            if (key.equals(_targetKey)){
                setSetpoint(computeSetpoint(SmartDashboard.getNumber(_targetKey)));
            }
        }
    };
    
    protected abstract double computeSetpoint(double newValue);
    protected abstract void writePIDOut(double output);
}
