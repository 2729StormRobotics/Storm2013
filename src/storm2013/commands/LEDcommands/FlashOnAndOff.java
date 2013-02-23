/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands.LEDcommands;

import storm2013.Robot;

/**
 *
 * @author evan1026
 */
public class FlashOnAndOff extends Flash {
    
    public FlashOnAndOff(Color color){
        this(color, Flash.DEFAULT_PERIOD);
    }
    public FlashOnAndOff(Color color, double period){
        super(new Color[] {color, Color.OFF}, period);
    }
    
    public void execute(){
        if (_period > 0){
            super.execute();
        }
        else{
            Robot.ledStrip.setColor(_colors[0].getRed(), _colors[0].getGreen(), _colors[0].getBlue());
        }
    }
}
