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
    
    private Stack _colorStack = new Stack();

    /**
     * Allocates space for the subsystem and it's various parts, and adds it to
     * the {@link LiveWindow}.
     */
    public LEDStrip() {
        _red   = new LED(6);
        _green = new LED(8);
        _blue  = new LED(7);
        LiveWindow.addActuator("LED", "Red",   _red);
        LiveWindow.addActuator("LED", "Green", _green);
        LiveWindow.addActuator("LED", "Blue",  _blue);
    }
    
    /**
     * Does nothing here, but it would set the command to be run when no other command
     * is using the subsystem.
     */
    protected void initDefaultCommand() {}
    
    public void setColor(int red,int green,int blue) {
        _red.set(red);
        _green.set(green);
        _blue.set(blue);
    }
    
    /**
     * Sets the new color for the LED strip.
     * @param color The new color, based on {@link StateColor} values.
     */
    private void setColor(StateColor color) {
        _red.set(color.getRed());
        _green.set(color.getGreen());
        _blue.set(color.getBlue());
    }
    
    /**
     * Adds a new color to the color stack and sets the LED's to output that color.
     * @param color 
     */
    public void pushColor(StateColor color){
        _colorStack.addElement(color);
        setColor(color);
    }
    
    /**
     * Pops the current color off the stack and sets the color to the next one.
     */
    public void popColor(){
        _colorStack.pop();
        if (!_colorStack.isEmpty()) {
            setColor((StateColor) _colorStack.peek());
        }
        else {
            setColor(StateColor.IDLE);
        }
    }
    
    /**
     * Gets the current LED color
     * @return The current color
     */
    public StateColor getColor(){
        return (StateColor) _colorStack.peek();
    }
    
    /**
     * Contains values for all of the colors that we want to use to represent different
     * robot actions.
     */
    public static class StateColor{
        
        public static final StateColor OFF               = new StateColor(0,     0,   0);
        public static final StateColor IDLE              = new StateColor(255, 255, 255);
        public static final StateColor ROBOT_MOVING      = new StateColor(255, 255, 255);
        public static final StateColor SHOOTER_ON_TARGET = new StateColor(255, 255, 255);
        public static final StateColor TARGETING         = new StateColor(255, 255, 255);
        public static final StateColor SHOOTING          = new StateColor(255, 255, 255);
        public static final StateColor SPINNING_UP       = new StateColor(255, 255, 255);
        public static final StateColor SPUN_UP           = new StateColor(255, 255, 255);
        
        //All of this is just a placeholder. Needs revision when we know what we're doing
        private int _red,_green,_blue;
        
        private StateColor(int red,int green,int blue) {
            _red   = red;
            _green = green;
            _blue  = blue;
        }
        
        /**
         * Returns the red value
         * @return the red value
         */
        public int getRed() {
            return _red;
        }
        
        /**
         * Returns the green value
         * @return the green value
         */
        public int getGreen() {
            return _green;
        }
        
        /**
         * Returns the blue value
         * @return the blue value
         */
        public int getBlue() {
            return _blue;
        }
    }
}
