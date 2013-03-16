package storm2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.commands.LEDcommands.SetModeMoving;
import storm2013.utilities.LED;

/** Subsystem to control the LED strip. */
public class LEDStrip extends Subsystem {
    LED _red,_green,_blue;

    public LEDStrip() {
        _red   = new LED(6);
        _green = new LED(8);
        _blue  = new LED(7);
        LiveWindow.addActuator("LED", "Red",   _red);
        LiveWindow.addActuator("LED", "Green", _green);
        LiveWindow.addActuator("LED", "Blue",  _blue);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new SetModeMoving());
    }
    
    /**
     * Sets the tri-color LEDs' color with red, green, and blue signals in the
     * [0,255] range. This is NOT a true RGB color, because the lights respond
     * weirdly to different signals.
     */
    public void setColor(int red, int green, int blue){
        _red.set(red);
        _green.set(green);
        _blue.set(blue);
    }
    
    /** Read current red value. */
    public int getRed(){
        return _red.get();
    }
    /** Read current green value. */
    public int getGreen(){
        return _green.get();
    }
    /** Read current blue value. */
    public int getBlue(){
        return _blue.get();
    }
    
}
