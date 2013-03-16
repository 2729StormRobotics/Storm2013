package storm2013.commands.LEDcommands;

/**
 * Color enumeration for the LED strip. Not exactly RGB, because of LED
 * weirdness.
 */
public class Color {
    public static final Color OFF         = new Color(0,     0,   0);   //off
    public static final Color MOVING      = new Color(255, 255, 255); //white
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
