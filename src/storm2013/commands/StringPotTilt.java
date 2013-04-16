package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;
import storm2013.commands.LEDcommands.SetColor;

public class StringPotTilt extends Command {
    private static double TOLERANCE = 0.1;
    
    private final Command lightCommand = new SetColor(0, 0, 255);
    private double _power,_target;
    private double _sign;
    
    public StringPotTilt(double power,double target) {
        _power = power;
        _target = target;
        requires(Robot.tilter);
    }
    
    protected double _getTarget() {
        return _target;
    }
    protected void _calcSign() {
        double delta = _target-Robot.tilter.readStringPot();
        _sign = (delta < -TOLERANCE ? -1 :
                 delta >  TOLERANCE ?  1 :
                                       0);
    }
    protected void _setTarget(double target) {
        _target = target;
        _calcSign();
    }

    protected void initialize() {
        _calcSign();
        lightCommand.start();
    }

    protected void execute() {
        Robot.tilter.move(_sign*_power);
    }

    protected boolean isFinished() {
        return _sign*(_target-Robot.tilter.readStringPot()) <= 0;
    }

    protected void end() {
        Robot.tilter.stop();
        lightCommand.cancel();
    }

    protected void interrupted() {
        end();
    }
}
