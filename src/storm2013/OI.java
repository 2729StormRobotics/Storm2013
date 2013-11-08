package storm2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import storm2013.commands.ChangeLEDMode;

/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {    
    private Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE);
    
    private Button changeLEDModeButton = new JoystickButton(driveJoystick, RobotMap.BUTTON_CHANGE_LED_MODE);
    
    public OI() {
        changeLEDModeButton.whenPressed(new ChangeLEDMode());
    }
}

