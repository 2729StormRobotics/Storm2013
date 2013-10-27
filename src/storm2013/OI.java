package storm2013;

import com.sun.squawk.io.BufferedWriter;
import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import javax.microedition.io.Connector;
import storm2013.commands.FeederTurn;
import storm2013.commands.LEDcommands.SetModeRainbowDanceParty;
import storm2013.commands.LEDcommands.Spaz;
import storm2013.commands.TargetPIDTilt;
import storm2013.commands.PrintAutonomousMove;
import storm2013.commands.Shoot;
import storm2013.commands.SpinDown;
import storm2013.commands.SpinTomahawk;
import storm2013.commands.SpinUp;
import storm2013.commands.TargetPIDTurn;
import storm2013.commands.TiltForDistance;
import storm2013.commands.TiltToCurrentDistance;
import storm2013.subsystems.Shooter;
import storm2013.subsystems.Tilter;
import storm2013.subsystems.Vision;
import storm2013.utilities.Target;

/** Connects the gamepads/joysticks to actual functionality on the robot. */
public class OI {    
    private Joystick driveJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DRIVE),
//                     shootJoystick = new Joystick(RobotMap.PORT_JOYSTICK_SHOOT),
                     debugJoystick = new Joystick(RobotMap.PORT_JOYSTICK_DEBUG);
    
    private Button //recordEncoderButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_PRINT_ENCODER),
                   slowModeButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SLOW),
                   //rainbowButton       = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_RAINBOW),
                   spazButton          = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SPAZ),
                   //target2ptTurnButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_2PT),
                   //target3ptTurnButton = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TARGET_3PT),
                   shootButton         = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SHOOT),
                   //shootFullButton     = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SHOOT_FULLCOURT),
                   spinDownButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_SPIN_DOWN),
                   //spinDown2Button     = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_SPIN_DOWN2),
//                   target2ptTiltButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TARGET_2PT),
//                   target3ptTiltButton = new JoystickButton(shootJoystick, RobotMap.JOYSHOOT_BUTTON_TARGET_3PT),
                   tomahawkButton      = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TOMAHAWK),
                   tomahawkBackButton  = new JoystickButton(driveJoystick, RobotMap.JOYDRIVE_BUTTON_TOMAHAWK_BACK),
//                   nextDistanceButton  = new Button() {
//                       public boolean get() {
//                           return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_DISTANCE) > 0.1;
//                       }
//                   },
//                   prevDistanceButton  = new Button() {
//                       public boolean get() {
//                           return shootJoystick.getRawAxis(RobotMap.JOYSHOOT_AXIS_DISTANCE) < -0.1;
//                       }
//                   },
//                   tiltToDistanceButton  = new JoystickButton(shootJoystick,RobotMap.JOYSHOOT_BUTTON_TILTTODIST),
//                   logPotButton          = new JoystickButton(shootJoystick,RobotMap.JOYSHOOT_BUTTON_LOGPOT),
                   feederAwayButton   = new Button() {
                       public boolean get() {
                           return driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_FEEDERTURN) < -0.1;
                       }
                   },
                   feederTowardButton   = new Button() {
                       public boolean get() {
                           return driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_FEEDERTURN) > 0.1;
                       }
                   },
                   controlShootButton = new JoystickButton(debugJoystick,RobotMap.JOYDEBUG_BUTTON_CONTROLSHOOT);
    
    public OI() {
        shootButton        .whenPressed(new Shoot(SpinUp.SPEED_SLOW));
//        shootFullButton    .whenPressed(new Shoot(SpinUp.SPEED_FULLCOURT));
        spinDownButton     .whenPressed(new SpinDown());
//        spinDown2Button    .whenPressed(new SpinDown());
//        recordEncoderButton.whenPressed(new PrintAutonomousMove(0.6, 0.5));
        tomahawkButton     .whenPressed(new SpinTomahawk(true));
        tomahawkBackButton .whenPressed(new SpinTomahawk(false));
        feederAwayButton   .whenPressed(new FeederTurn(false));
        feederTowardButton .whenPressed(new FeederTurn(true));
        
        TargetPIDTurn turn2ptAim = new TargetPIDTurn(Target.TwoPT,   1.0, true),
                      turn3ptAim = new TargetPIDTurn(Target.ThreePT, 1.0, true);
        TargetPIDTilt tilt2ptAim = new TargetPIDTilt(Target.TwoPT,   1.0, true),
                      tilt3ptAim = new TargetPIDTilt(Target.ThreePT, 1.0, true);
        
        SmartDashboard.putData("Turn 3pt PID",turn3ptAim.getPIDController());
        SmartDashboard.putData("Tilt 3pt PID",tilt3ptAim.getPIDController());
        
//        target2ptTurnButton.whileHeld(turn2ptAim);
//        target3ptTurnButton.whileHeld(turn3ptAim);
//        target2ptTiltButton.whileHeld(tilt2ptAim);
//        target3ptTiltButton.whenPressed(new TiltToCurrentDistance(Tilter.SPEED_DEFAULT));
        
//        nextDistanceButton.whenPressed(new Command() {
//            {
//                requires(Robot.vision);
//            }
//            protected void initialize() {
//                Robot.vision.nextDistance();
//            }
//            protected void execute() {}
//            protected boolean isFinished() {
//                return true;
//            }
//            protected void end() {}
//            protected void interrupted() {}
//        });
//        prevDistanceButton.whenPressed(new Command() {
//            {
//                requires(Robot.vision);
//            }
//            protected void initialize() {
//                Robot.vision.prevDistance();
//            }
//            protected void execute() {}
//            protected boolean isFinished() {
//                return true;
//            }
//            protected void end() {}
//            protected void interrupted() {}
//        });
//        tiltToDistanceButton.whenPressed(new TiltForDistance(Tilter.SPEED_DEFAULT,Vision.Distance.NEAR));
        
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
//        rainbowButton.whileHeld(new SetModeRainbowDanceParty());
        spazButton.whileHeld(new Spaz());
//        logPotButton.whenPressed(new Command() {
//            private FileConnection file = null;
//            BufferedWriter out;
//            {
//                try {
//                    file = (FileConnection) Connector.open("file:///Pot.log", Connector.READ_WRITE);
//
//                    if(!file.exists()) {
//                        file.create();
//                    }
//
//                    OutputStream output = file.openOutputStream(Long.MAX_VALUE);
//                    out = new BufferedWriter(new OutputStreamWriter(output));
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//
//            
//            protected void initialize() {
//                String text = Robot.vision.getCurrDistance().getValue() + ":" + Robot.tilter.readStringPot();
//                try {
//                    out.write(text+"\n");
//                    out.flush();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//            protected void execute() {}
//            protected boolean isFinished() {
//                return true;
//            }
//            protected void end() {}
//            protected void interrupted() {}
//        });
    }
    
    // When a joystick is in its zero position, it will not necessarily read
    // zero. This makes sure that everything within that range reads zero.
    private double _zeroDeadzone(double joyValue,double dead) {
        return Math.abs(joyValue) > dead ? joyValue : 0;
    }
    
    public double getLeftDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_LEFT),0.15)*(slowModeButton.get() ? 0.7 : 1);
    }
    
    public double getRightDrive() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_DRIVE_RIGHT),0.15)*(slowModeButton.get() ? 0.7 : 1);
    }
    
    public double getTilterAxis() {
        return _zeroDeadzone(-driveJoystick.getRawAxis(RobotMap.JOYDRIVE_AXIS_TILTER),0.15);
    }
}

