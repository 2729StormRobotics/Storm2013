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
    
    private final PIDController _controller;
    
    private final String _targetFoundKey;
    private final String _targetKey;
    private final Timer _timeyWimey = new Timer();
    private final double _timeout;
    private final boolean _willTimeout;
    private final boolean _continuous;
    
    public TargetPIDCommand(Target target, Target.Axis axis, double timeout,boolean continuous,
                            double p,double i,double d,double tolerance,double maxOutput){
        super(p,i,d);
        requires(Robot.tilter);
        _controller = super.getPIDController();
        _timeout = timeout;
        _willTimeout = (timeout >= 0);
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
        setSetpoint(getPosition()+SmartDashboard.getNumber(_targetKey));
        NetworkTable.getTable("SmartDashboard").addTableListener(_listener);
        _controller.enable();
        _timeyWimey.reset();
        _timeyWimey.start();
        _latencyTimer.reset();
        _latencyTimer.start();
    }

    protected void execute() {
        if (_willTimeout){
            if(SmartDashboard.getBoolean(_targetFoundKey)){
                _timeyWimey.reset();
                _controller.enable();
            }
            if(_timeyWimey.get() > _timeout) {
                System.out.println("Timing out!");
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
        return super.getPIDController();
    }
    
    private Timer _latencyTimer = new Timer();
    
    private ITableListener _listener = new ITableListener() {
        public void valueChanged(ITable source, String key, Object value, boolean isNew) {
            if (key.equals(_targetKey)){
                setSetpoint(getPosition()+((Double)value).doubleValue());
                SmartDashboard.putNumber("NetworkTable latency (ms)", _latencyTimer.get()/1.0e3);
                _latencyTimer.reset();
            }
        }
    };
    
    protected abstract void writePIDOut(double output);
}
