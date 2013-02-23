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
    
    private static final double PERIOD_SCALAR = 0.05;
    
    public SetModeMoving(){
        super(Color.MOVING);
    }
    
    public void execute(){
        super.execute();
        
        double frequency = (Math.abs(Robot.driveTrain.getLeftSpeed()) + Math.abs(Robot.driveTrain.getRightSpeed())) / 2;
        double speedVal = 1/frequency;
        if(frequency == 0) {
            speedVal = -1;
        }
        
        setPeriod(speedVal * PERIOD_SCALAR);
    }
}
