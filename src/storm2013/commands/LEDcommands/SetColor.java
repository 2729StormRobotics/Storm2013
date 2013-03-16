package storm2013.commands.LEDcommands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/** Sets LED color, then leaves it that way until interrupted somehow. */
public class SetColor extends Command {
    private int _red,
                _green,
                _blue;
    
    public SetColor(int red, int green, int blue){
        _red = red;
        _green = green;
        _blue = blue;
        requires(Robot.ledStrip);
    }
    
    public SetColor(Color color){
        this(color.getRed(),color.getGreen(),color.getBlue());
    }
    
    protected void initialize() {
        Robot.ledStrip.setColor(_red, _green, _blue);
    }
    protected void execute() {}
    protected boolean isFinished() {
        return false;
    }
    protected void end() {}
    protected void interrupted() {
        end();
    }
    
}
