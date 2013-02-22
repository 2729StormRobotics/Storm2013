/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

/**
 *
 * @author evan1026
 */
public class StateColor {
    
    public static final StateColor OFF         = new StateColor(0,   0,   0);   //off
    public static final StateColor IDLE        = new StateColor(255, 255, 255); //white
    public static final StateColor MOVING      = new StateColor(255, 165, 0);   //orange
    public static final StateColor TARGETING   = new StateColor(0,   0,   255); //blue
    public static final StateColor ON_TARGET   = new StateColor(160, 32,  240); //purple
    public static final StateColor SPINNING_UP = new StateColor(255, 0,   0);   //red
    public static final StateColor SPUN_UP     = new StateColor(255, 255, 0);   //yellow
    public static final StateColor FIRING      = new StateColor(0,   255, 0);   //green
    
    
    private int _red,
                _green,
                _blue;
    
    private StateColor(int red, int green, int blue){
        _red = red;
        _green = green;
        _blue = blue;
    }
    
    public int getRed(){
        return _red;
    }
    public int getGreen(){
        return _green;
    }
    public int getBlue(){
        return _blue;
    }
}
