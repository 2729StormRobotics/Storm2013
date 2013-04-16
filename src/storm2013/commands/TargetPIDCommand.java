package storm2013.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import storm2013.utilities.Target;

/**
 * Base for {@link TargetPIDTilt} and {@link TargetPIDTurn}
 * @author ginto
 */
public abstract class TargetPIDCommand extends PIDCommand {
    private static final ITable _dashboardTable = NetworkTable.getTable("SmartDashboard");
    
    private final PIDController _controller;
    
    private final String _targetFoundKey;
    private final String _targetKey;
    private final Timer _timeyWimey = new Timer(),
                        _onTarget   = new Timer();
    private final double _timeout;
    private final boolean _willTimeout;
    private final boolean _continuous;
    
    /**
     * @param target     The {@link Target} to aim at. Used to find network table keys
     * @param axis       The axis (x or y) to look for
     * @param timeout    How long (seconds) until the {@link PIDCommand} times out
     * @param continuous If continuous, it never ends!!! MUAHAHAHA!!!!
     * @param p          P value for PID controller
     * @param i          I value for PID controller
     * @param d          D value for PID controller
     * @param tolerance  the tolerance for the PID controller
     * @param maxOutput  the absolute value of the maximum output
     */
    public TargetPIDCommand(Target target, Target.Axis axis, double timeout,boolean continuous,
                            double p,double i,double d,double tolerance,double maxOutput,double period){
        super(p,i,d,period);
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
        useCameraValue(SmartDashboard.getNumber(_targetKey,0));
        _dashboardTable.addTableListener(_listener);
        _controller.enable();
        _timeyWimey.reset();
        _timeyWimey.start();
        _onTarget.reset();
        _onTarget.start();
        _latencyTimer.reset();
        _latencyTimer.start();
    }

    protected void execute() {
        if (_willTimeout){
            if(SmartDashboard.getBoolean(_targetFoundKey,false)){
                _timeyWimey.reset();
                _controller.enable();
            }
            if(_timeyWimey.get() > _timeout) {
                System.out.println("Timing out!");
                _controller.disable();
            }
        }
        if(!_controller.onTarget()) {
            _onTarget.reset();
        }
    }

    protected boolean isFinished() {
        return !_continuous && (_onTarget.get() >= 0.3);
    }
    
    protected void end() {
        _dashboardTable.removeTableListener(_listener);
    }
    
    protected void interrupted() {
        end();
    }

    /**
     * Returns the internal PIDController. Needed because
     * PIDCommand.getPIDController() is protected.
     */
    public PIDController getPIDController() {
        return super.getPIDController();
    }
    
    private Timer _latencyTimer = new Timer();
    
    private ITableListener _listener = new ITableListener() {
        public void valueChanged(ITable source, String key, Object value, boolean isNew) {
            if (key.equals(_targetKey)){
                Double numValue = (Double)value;
                if(numValue != null) {
                    useCameraValue(numValue.doubleValue());
//                    SmartDashboard.putNumber("NetworkTable latency (ms)", _latencyTimer.get()*1.0e3);
                    _latencyTimer.reset();
                }
            }
        }
    };
    
    // To be implemented by subclasses.
    protected abstract void useCameraValue(double value);
    protected abstract void writePIDOut(double output);
}
