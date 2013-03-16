package storm2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.LEDcommands.SetModeRainbowDanceParty;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.PrintAutonomousMove;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.SpinTomahawk;
import storm2013.commands.SpinUp;
import storm2013.commands.TargetPIDTurn;
import storm2013.utilities.Target;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {    
    private Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
                     shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
                     debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private Button recordEncoderButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_PRINT_ENCODER),
                   slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW),
                   rainbowButton       = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_RAINBOW),
                   target2ptTurnButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_2PT),
                   target3ptTurnButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_3PT),
                   shootButton         = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SHOOT),
                   shootFullButton     = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SHOOT_FULLCOURT),
                   spinDownButton      = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_DOWN),
                   target2ptTiltButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TARGET_2PT),
                   target3ptTiltButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TARGET_3PT),
                   tomahawkButton      = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TOMAHAWK),
                   tomahawkBackButton  = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TOMAHAWK_BACK),
                   nextDistanceButton  = new Button() {
                       public boolean get() {
                           return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_DISTANCE) > 0.1;
                       }
                   },
                   prevDistanceButton  = new Button() {
                       public boolean get() {
                           return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_DISTANCE) < -0.1;
                       }
                   },
                   controlShootButton = new JoystickButton(debugJoystick,RobotMap.JOYDEBUG_BUTTON_CONTROLSHOOT);
    
    public OI() {
        shootButton.whenPressed(new Shoot());
        shootFullButton.whenPressed(new Shoot(SpinUp.SPEED_FULLCOURT));
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
        
        nextDistanceButton.whenPressed(new Command() {
            {
                requires(Robot.vision);
            }
            protected void initialize() {
                Robot.vision.nextDistance();
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        prevDistanceButton.whenPressed(new Command() {
            {
                requires(Robot.vision);
            }
            protected void initialize() {
                Robot.vision.prevDistance();
            }
            protected void execute() {}
            protected boolean isFinished() {
                return true;
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
                Robot.shooter.setSetpoint((-debugJoystick.getRawAxis(RobotMap.JOYDEBUG_AXIS_SHOOTSPEED)+1)/2.0*6000);
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {}
        });
        rainbowButton.whileHeld(new SetModeRainbowDanceParty());
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

