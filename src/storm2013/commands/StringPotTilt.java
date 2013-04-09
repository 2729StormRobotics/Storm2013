package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

public class StringPotTilt extends Command {
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
        _sign = (delta < -0.1 ? -1 :
                 delta >  0.1 ?  1 :
                                 0);
    }
    protected void _setTarget(double target) {
        _target = target;
        System.out.println("Moving to pot = " + target);
        _calcSign();
    }

    protected void initialize() {
        _calcSign();
    }

    protected void execute() {
        Robot.tilter.move(_sign*_power);
    }

    protected boolean isFinished() {
        return _sign*(_target-Robot.tilter.readStringPot()) <= 0;
    }

    protected void end() {
        Robot.tilter.stop();
    }

    protected void interrupted() {
        end();
    }
}
