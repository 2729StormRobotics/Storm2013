/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Stack;
import storm2013.utilities.LED;

/**
 *
 * @author evan1026
 */
public class LEDStrip extends Subsystem {
    
    LED _red,_green,_blue;
    
    private StateColor _color      = StateColor.OFF;
    private Stack      _colorStack = new Stack();

    public LEDStrip() {
        _red   = new LED(6);
        _green = new LED(7);
        _blue  = new LED(8);
        LiveWindow.addActuator("LED", "Red",   _red);
        LiveWindow.addActuator("LED", "Green", _green);
        LiveWindow.addActuator("LED", "Blue",  _blue);
    }
    
    protected void initDefaultCommand() {}
    
    private void setColor(StateColor color) {
        _color = color;
        _red.set(color.getRed());
        _green.set(color.getGreen());
        _blue.set(color.getBlue());
    }
    
    public void pushColor(StateColor color){
        _colorStack.addElement(color);
        setColor(color);
    }
    public void popColor(){
        if (!_colorStack.isEmpty()) _color = (StateColor) _colorStack.pop();
        else _color = StateColor.IDLE;
    }
    public StateColor getColor(){
        return _color;
    }
    
    public static class StateColor{
        
        public static StateColor OFF;
        public static StateColor IDLE;
        public static StateColor ROBOT_MOVING;
        public static StateColor SHOOTER_ON_TARGET;
        public static StateColor TARGETING;
        public static StateColor SHOOTING;
        public static StateColor SPINNING_UP;
        public static StateColor SPUN_UP;
        
        //All of this is just a placeholder. Needs revision when we know what we're doing
        private int _red,_green,_blue; 
        
        private StateColor(int red,int green,int blue) {
            _red   = red;
            _green = green;
            _blue  = blue;
        }
        public int getRed(){
            return _red;
        }
        public int getGreen() {
            return _green;
        }
        public int getBlue() {
            return _blue;
        }
    }
}
