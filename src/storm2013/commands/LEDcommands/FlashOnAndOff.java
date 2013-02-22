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
    
    public FlashOnAndOff(Color color){
        this(color, Flash.DEFAULT_PERIOD);
    }
    public FlashOnAndOff(Color color, long period){
        super(new Color[] {color, Color.OFF}, period);
    }
}
