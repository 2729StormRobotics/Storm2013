package storm2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Stack;
import storm2013.utilities.LED;

/**
 * Subsystem to control the LED strip.
 * @author evan1026
 */
public class LEDStrip extends Subsystem {
    LED _red,_green,_blue;

    /**
     * Allocates space for the subsystem and it's various parts, and adds it to
     * the {@link LiveWindow}.
     */
    public LEDStrip() {
        _red   = new LED(6);
        _green = new LED(7);
        _blue  = new LED(8);
        LiveWindow.addActuator("LED", "Red",   _red);
        LiveWindow.addActuator("LED", "Green", _green);
        LiveWindow.addActuator("LED", "Blue",  _blue);
    }
    
    /**
     * Does nothing here, but it would set the command to be run when no other command
     * is using the subsystem.
     */
    protected void initDefaultCommand() {}
    
    public void setColor(int red, int green, int blue){
        _red.set(red);
        _green.set(green);
        _blue.set(blue);
    }
    
    public int getRed(){
        return _red.get();
    }
    public int getGreen(){
        return _green.get();
    }
    public int getBlue(){
        return _blue.get();
    }
    
}
