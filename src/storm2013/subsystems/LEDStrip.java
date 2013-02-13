/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.util.Stack;

/**
 *
 * @author evan1026
 */
public class LEDStrip extends Subsystem {
    
    private StateColor _color      = StateColor.OFF;
    private Stack      _colorStack = new Stack();
    
    protected void initDefaultCommand() {
    }
    
    public void pushColor(StateColor color){
        _colorStack.addElement(color);
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
        private int _value; 
        
        private StateColor(int value){
            _value = value;
        }
        public int getValue(){
            return _value;
        }
    }
}
