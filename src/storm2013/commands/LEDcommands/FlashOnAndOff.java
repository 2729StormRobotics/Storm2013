/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

/**
 *
 * @author evan1026
 */
public class FlashOnAndOff extends Flash {
    
    public FlashOnAndOff(int red, int green, int blue){
        super(red, 0, green, 0, blue, 0);
    }
    
    public FlashOnAndOff(int red, int green, int blue, long period){
        super(red, 0, green, 0, blue, 0, period);
    }
    
    public FlashOnAndOff(StateColor color){
        super(color, StateColor.OFF);
    }
}
