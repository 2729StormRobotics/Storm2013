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
    private int   _red1,   _red2,
                  _green1, _green2,
                  _blue1,  _blue2;
    private long  _period;

    public Flash(int red1, int red2, int green1, int green2, int blue1, int blue2) {
        this(red1, red2, blue1, blue2, green1, green2, DEFAULT_PERIOD);
    }
    public Flash(StateColor color1, StateColor color2){
        this(color1.getRed(), color2.getRed(), color1.getGreen(), color2.getGreen(), color1.getBlue(), color2.getBlue());
    }
    public Flash(int red1, int red2, int green1, int green2, int blue1, int blue2, long period) {
        _red1 = red1;
        _red2 = red2;
        _green1 = green1;
        _green2 = green2;
        _blue1 = blue1;
        _blue2 = blue2;
        _period = period;
    }
    
    protected void initialize() {
        TimerTask task = new TimerTask(){
            boolean one;
            public void run() {
                if (one){
                    Robot.ledStrip.setColor(_red1, _blue1, _green1);
                }
                else{
                    Robot.ledStrip.setColor(_red2, _blue2, _green2);
                }
                one = !one;
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
