/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import storm2013.RobotMap;
import storm2013.commands.DoNothing;

/**
 *
 * @author evan1026
 */
public class AddressableLEDs extends Subsystem {

    private DigitalOutput out1 = new DigitalOutput(RobotMap.PORT_LED_STRIP_1);
    private DigitalOutput out2 = new DigitalOutput(RobotMap.PORT_LED_STRIP_2);
    
    private int currentMode = 0;
    
    public AddressableLEDs(){
        out1.set(false); //I know they should be false already, but it doesn't hurt to make sure
        out2.set(false);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new DoNothing());
    }
    
    public void nextMode(){
        currentMode++;
        currentMode %= 4;
        
        switch (currentMode){
            case 0:  out1.set(false);
                     out2.set(false);
                     break;
            case 1:  out1.set(true);
                     out2.set(false);
                     break;
            case 2:  out1.set(false);
                     out2.set(true);
                     break;
            case 3:  out1.set(true);
                     out2.set(true);
                     break;
            default: break;
        }
    }
    
}
