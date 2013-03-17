/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Random;
import storm2013.Robot;
import storm2013.subsystems.LEDStrip;

/**
 * Sets the {@link LEDStrip} color to some random color 20 times a second, or at 
 * some other specified rate.
 * @author evan1026
 */
public class Spaz extends Command {
    private static final double DEFAULT_PERIOD = 1.0/20;
    private static final Color[] colors = {new Color(255,0,0), new Color(255,255,0), new Color(0,255,0),
                                           new Color(0,255,255), new Color(0,0,255), new Color(255,0,255)};
    
    private double _period;
    private Timer  _timer = new Timer();
    private Random _rand  = new Random();
    
    public Spaz(){
        this(DEFAULT_PERIOD);
    }
    
    public Spaz(double period){
        _period = period;
        requires(Robot.ledStrip);
    }
    
    protected void initialize() {
        _timer.start();
    }

    protected void execute() {
        double time = _timer.get();
        
        if (time >= _period){
            Color c = colors[_rand.nextInt(colors.length)];
            Robot.ledStrip.setColor(c.getRed(),c.getBlue(),c.getGreen());
            _timer.reset();
        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
