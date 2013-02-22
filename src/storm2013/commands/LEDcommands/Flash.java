/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

import edu.wpi.first.wpilibj.command.Command;
import java.util.Timer;
import java.util.TimerTask;
import storm2013.Robot;

/**
 *
 * @author evan1026
 */
public class Flash extends Command {
    
    public static final long DEFAULT_PERIOD = 1000; //once a second
    
    private Timer _flasher = new Timer();
    private Color[] _colors;
    private long  _period;

    public Flash(Color[] colors) {
        this(colors, DEFAULT_PERIOD);
    }
    
    public Flash(Color[] colors, long period) {
        _colors = colors;
        _period = period;
    }
    
    protected void initialize() {
        TimerTask task = new TimerTask(){
            int index;
            public void run() {
                Robot.ledStrip.setColor(_colors[index].getRed(), _colors[index].getGreen(), _colors[index].getBlue());
                index = (index + 1) % _colors.length;
            }
        };
        _flasher.schedule(task, 0L, _period);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        _flasher.cancel();
    }

    protected void interrupted() {
        end();
    }
    
}
