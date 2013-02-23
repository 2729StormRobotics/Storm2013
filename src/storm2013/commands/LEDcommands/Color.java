/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

/**
 *
 * @author evan1026
 */
public class Color {
    
    public static final Color OFF         = new Color(0,     0,   0);   //off
    public static final Color MOVING      = new Color(255, 255, 255); //white
    public static final Color TARGETING   = new Color(0,     0, 255); //blue
    public static final Color ON_TARGET   = new Color(160,  32, 240); //purple
    public static final Color SPINNING_UP = new Color(0,   255,   0);   //green
    public static final Color FIRING      = new Color(255,   0,   0);   //red
    
    private int _red,
                _green,
                _blue;
    
    public Color(int red, int green, int blue){
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
