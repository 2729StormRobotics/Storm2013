package storm2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.commands.LowerTilter;
import storm2013.commands.PrintAutonomousMove;
import storm2013.commands.RaiseTilter;
import storm2013.commands.SpinTomahawk;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    private Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE);
    
    private JoystickButton tomahawkButton      = new JoystickButton(driveJoystick, RobotMap.BUTTON_SHOOT),
                           recordEncoderButton = new JoystickButton(driveJoystick, RobotMap.BUTTON_PRINT_ENCODER),
                           tilterUpButton      = new JoystickButton(driveJoystick, RobotMap.BUTTON_TILTER_UP),
                           tilterDownButton    = new JoystickButton(driveJoystick, RobotMap.BUTTON_TILTER_DOWN);
    
    public OI() {
        tomahawkButton.whenPressed(new SpinTomahawk());
        recordEncoderButton.whenPressed(new PrintAutonomousMove(0.6, 0.5));
        tilterUpButton.whileHeld(new RaiseTilter());
        tilterDownButton.whileHeld(new LowerTilter());
    }
    
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
    }
    
    private double _applyExponential(double joyValue) {
        if(joyValue == 0) {
            return 0;
	}
	double sign = (joyValue > 0) ? 1 : -1;
        return sign*joyValue*joyValue/Math.sqrt(Math.abs(joyValue));
    }
    
    public double getDriveAxis() {
        return _applyExponential(_zeroDeadzone(-driveJoystick.getRawAxis(2),0.1));
    }
    
    public double getTurnAxis() {
        return _applyExponential(_zeroDeadzone(driveJoystick.getRawAxis(3)/2,0.3));
    }
    
    public double getFlipperAxis() {
        return driveJoystick.getRawAxis(4);
    }
}

