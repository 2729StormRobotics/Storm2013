package storm2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.PrintAutonomousMove;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.SpinTomahawk;
import storm2013.commands.TargetPIDTurn;
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
                     shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
                     debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private Button recordEncoderButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_PRINT_ENCODER),
                   slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW),
                   target2ptTurnButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_2PT),
                   target3ptTurnButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_3PT),
                   shootButton         = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SHOOT),
                   spinDownButton      = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_DOWN),
                   target2ptTiltButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TARGET_2PT),
                   target3ptTiltButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TARGET_3PT),
                   tomahawkButton      = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TOMAHAWK),
                   tomahawkBackButton  = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TOMAHAWK_BACK),
                   tilterSafety1Button = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TILTER_SAFETY_1),
                   tilterSafety2Button = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TILTER_SAFETY_2),
                   tilterSafetyButton  = new Button() {
                       public boolean get() {
                           return tilterSafety1Button.get() && tilterSafety2Button.get();
                       }
                   },
                   nextDistanceButton  = new Button() {
                       public boolean get() {
                           return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_DISTANCE) > 0;
                       }
                   },
                   prevDistanceButton  = new Button() {
                       public boolean get() {
                           return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_DISTANCE) < 0;
                       }
                   },
                   controlShootButton = new JoystickButton(debugJoystick,RobotMap.JOYDEBUG_AXIS_SHOOTSPEED);
    
    public OI() {
        shootButton.whenPressed(new Shoot());
        spinDownButton.whenPressed(new SpinDown());
        recordEncoderButton.whenPressed(new PrintAutonomousMove(0.6, 0.5));
        tomahawkButton.whenPressed(new SpinTomahawk(true));
        tomahawkBackButton.whenPressed(new SpinTomahawk(false));
        
        TargetPIDTurn turn2ptAim = new TargetPIDTurn(Target.TwoPT,   1.0, true),
                      turn3ptAim = new TargetPIDTurn(Target.ThreePT, 1.0, true);
        TargetPIDTilt tilt2ptAim = new TargetPIDTilt(Target.TwoPT,   1.0, true),
                      tilt3ptAim = new TargetPIDTilt(Target.ThreePT, 1.0, true);
        
        SmartDashboard.putData("Turn 3pt PID",turn3ptAim.getPIDController());
        SmartDashboard.putData("Tilt 3pt PID",tilt3ptAim.getPIDController());
        
        target2ptTurnButton.whileHeld(turn2ptAim);
        target3ptTurnButton.whileHeld(turn3ptAim);
        target2ptTiltButton.whileHeld(tilt2ptAim);
        target3ptTiltButton.whileHeld(tilt3ptAim);
        
        tilterSafetyButton.whileHeld(new Command() {
            {
                requires(Robot.tilter);
            }
            protected void initialize() {}
            protected void execute() {
                Robot.tilter.disableSafety();
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        
        nextDistanceButton.whenPressed(new Command() {
            {
                requires(Robot.vision);
            }
            protected void initialize() {}
            protected void execute() {
                Robot.vision.nextDistance();
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        prevDistanceButton.whenPressed(new Command() {
            {
                requires(Robot.vision);
            }
            protected void initialize() {}
            protected void execute() {
                Robot.vision.prevDistance();
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        
        controlShootButton.whileHeld(new Command() {
            {
                requires(Robot.shooter);
            }
            protected void initialize() {}
            protected void execute() {
                Robot.shooter.setSetpoint((-debugJoystick.getRawAxis(RobotMap.JOYDEBUG_AXIS_SHOOTSPEED)+1)/2.0*3400);
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {}
        });
    }
    
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
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

