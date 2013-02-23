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
public class SetModeMoving extends FlashOnAndOff {
    
    private static final double PERIOD_SCALAR = 1;
    
    public SetModeMoving(){
        super(Color.MOVING);
    }
    
    public void execute(){
        super.execute();
        
        double speedVal = 1/Math.abs((Robot.driveTrain.getLeftSpeed() + Robot.driveTrain.getRightSpeed()) / 2);
        setPeriod(speedVal * PERIOD_SCALAR);
    }
}
