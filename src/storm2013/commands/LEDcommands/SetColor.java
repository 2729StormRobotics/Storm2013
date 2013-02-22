/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author evan1026
 */
public class SetColor extends Command {

    private int _red,
                _green,
                _blue;
    
    public SetColor(int red, int green, int blue){
        _red = red;
        _green = green;
        _blue = blue;
    }
    
    public SetColor(Color color){
        _red = color.getRed();
        _green = color.getGreen();
        _blue = color.getBlue();
    }
    
    protected void initialize() {
        Robot.ledStrip.setColor(_red, _green, _blue);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
    
}
