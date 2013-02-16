package storm2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.TargetPIDTurn;
import storm2013.commands.PrintAutonomousMove;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.SpinTomahawk;
import storm2013.utilities.Target;

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
    
    private Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
                     shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT);
    
    private Button recordEncoderButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_PRINT_ENCODER),
                   slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW),
                   target2ptButton     = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_2PT),
                   shootButton         = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SHOOT),
                   spinDownButton      = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_DOWN),
                   resetTomahawkButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_RESET_TOMAHAWK);
    
    public OI() {
        shootButton.whenPressed(new Shoot());
        spinDownButton.whenPressed(new SpinDown());
        recordEncoderButton.whenPressed(new PrintAutonomousMove(0.6, 0.5));
        resetTomahawkButton.whenPressed(new SpinTomahawk());
//        tilterUpButton.whileHeld(new RaiseTilter());
//        tilterDownButton.whileHeld(new LowerTilter());
        
        TargetPIDTurn turnAim = new TargetPIDTurn(Target.TwoPT, 1.0, true);
        SmartDashboard.putData("Turn PID",turnAim.getPIDController());
        
//        TargetPIDTilt tiltAim = new TargetPIDTilt(Target.TwoPT, 1.0,false);
//        SmartDashboard.putData("Tilt PID",turnAim.getPIDController());
//        
//        CommandGroup aim = new CommandGroup("2pt Aim");
//        aim.addParallel(turnAim);
//        aim.addParallel(tiltAim);
        
        SmartDashboard.putData(turnAim);
        
        target2ptButton.whileHeld(turnAim);
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
    
    public double getLeftDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_LEFT),0.15)*(slowModeButton.get() ? 0.5 : 1);
    }
    
    public double getRightDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_RIGHT),0.15)*(slowModeButton.get() ? 0.5 : 1);
    }
    
    public double getTilterAxis() {
        return _zeroDeadzone(-shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_TILTER),0.15);
    }
}

