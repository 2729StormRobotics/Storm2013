package storm2013.commands.LEDcommands;

import storm2013.Robot;

/** Flash one color on and off (or solid on if period == 0). */
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
