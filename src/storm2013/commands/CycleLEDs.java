/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author Joe
 */
public class CycleLEDs extends Command {
    private final Timer _timer = new Timer();
    private int _value = 0;
    
    public CycleLEDs() {
        requires(Robot.ledStrip);
    }

    protected void initialize() {
        _value = 0;
        _timer.reset();
        _timer.start();
    }

    protected void execute() {
        if(_timer.get() > 0.25) {
            _timer.reset();
            ++_value;
            _value %= 8;
        }
        Robot.ledStrip.setColor(((_value&1) == 1)?255:0, ((_value&2) == 2)?255:0, ((_value&4) == 4)?255:0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
