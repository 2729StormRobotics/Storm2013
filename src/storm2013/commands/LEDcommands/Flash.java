package storm2013.commands.LEDcommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/** Flash through a series of colors */
public class Flash extends Command {
    public static final double DEFAULT_PERIOD = 1; //once a second
    
    protected Timer _timer = new Timer();
    protected Color[] _colors;
    protected double  _period;
    protected int index;

    public Flash(Color[] colors) {
        this(colors, DEFAULT_PERIOD);
    }
    
    /**
     * @param colors Sequence of colors
     * @param period Time each color is on, in seconds
     */
    public Flash(Color[] colors, double period) {
        _colors = colors;
        _period = period;
        requires(Robot.ledStrip);
    }
    
    protected void initialize() {
        _timer.start();
    }
    protected void execute() {
        double time = _timer.get();
        
        if(time >= _period){
            Robot.ledStrip.setColor(_colors[index].getRed(), _colors[index].getGreen(), _colors[index].getBlue());
            index = (index + 1) % _colors.length;
            _timer.reset();
        }
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
        _timer.stop();
    }
    protected void interrupted() {
        end();
    }
    
    protected void setPeriod(double period){
        _period = period;
    }
    protected double getPeriod(){
        return _period;
    }
}
