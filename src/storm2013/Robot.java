package storm2013;

import storm2013.commands.autonomous.JustShoot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.LEDcommands.SetModeRainbowDanceParty;
import storm2013.commands.SpinDown;
import storm2013.commands.autonomous.PyramidShoot;
import storm2013.subsystems.DriveTrain;
import storm2013.subsystems.LEDStrip;
import storm2013.subsystems.Shooter;
import storm2013.subsystems.Tilter;
import storm2013.subsystems.Tomahawk;
import storm2013.subsystems.Vision;
import storm2013.utilities.LoadSensor;

/** 
 * This is the robot's "Main class" which is run by the VM.
 */
public class Robot extends IterativeRobot {
    // All subsystems are accessible by Robot.name
    public static OI         oi;
    public static DriveTrain driveTrain;
    public static Shooter    shooter;
    public static Tilter     tilter;
    public static Tomahawk   tomahawk;
    public static LEDStrip   ledStrip;
    public static Vision     vision;
    
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
    /** Called on robot boot. */
    public void robotInit() {
        driveTrain = new DriveTrain();
        shooter    = new Shooter();
        tilter     = new Tilter();
        tomahawk   = new Tomahawk();
        ledStrip   = new LEDStrip();
        vision     = new Vision();
        // Initialize OI last so it doesn't try to access null subsystems
        oi         = new OI();

        // The names, and corresponding Commands of our autonomous modes
        autonomiceNames = new String[]{"Rainbow Dance Party!", "Just shoot", "Shoot through Pyramid"};
        autonomice = new Command[]{new SetModeRainbowDanceParty(), new JustShoot(), new PyramidShoot()};

        // Configure and send the SendableChooser, which allows autonomous modes
        // to be chosen via radio button on the SmartDashboard
        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);

        // Put the Shooter PID on the dashboard so it can be enabled and stuff
        SmartDashboard.putData("Shooter PID",shooter.getPIDController());
        
        // To make sure the Accelerator is running
        shooter.enable();
        // To make sure it isn't accelerating
        new SpinDown().start();
        
        // Send sensor info to the SmartDashboard periodically
        new Command("Sensor feedback") {
            protected void initialize() {}
            protected void execute() {
                SmartDashboard.putNumber("Wheel Speed (RPM)", shooter.getSpeedRpm());
                SmartDashboard.putNumber("Wheel signal", shooter.getMotorValRaw());
                SmartDashboard.putBoolean("Tomahawk forward?", tomahawk.isForward());
                SmartDashboard.putBoolean("Tilter at top?", tilter.isTopLimitTriggered());
            }
            protected boolean isFinished() {
                return false;
            }
            protected void end() {}
            protected void interrupted() {
                end();
            }
        }.start();
    }

    /** Called at the start of autonomous mode. */
    public void autonomousInit() {
        shooter.enable();
        SmartDashboard.putBoolean("Enabled", true);
        if (teleop != null) {
            teleop.cancel();
        }
        autonomouse = (Command) chooser.getSelected();
        if (autonomouse != null) {
            autonomouse.start();
        }
    }

    /**
     * Called during autonomous whenever a new driver station packet arrives
     * (about every 1/50 of a second).
     */
    public void autonomousPeriodic() {
        // Runs commands & stuff.
        Scheduler.getInstance().run();
    }

    /** Called at the start of teleop mode. */
    public void teleopInit() {
        shooter.enable();
        SmartDashboard.putBoolean("Enabled", true);
        if (autonomouse != null) {
            autonomouse.cancel();
        }
        if (teleop != null) {
            teleop.start();
        }
    }

    /**
     * Called during teleop whenever a new driver station packet arrives (about
     * every 1/50 of a second).
     */
    public void teleopPeriodic() {
        // Runs commands & stuff
        Scheduler.getInstance().run();
    }

    /** Called at the start of test mode */
    public void testInit() {
        SmartDashboard.putBoolean("Enabled", false);
    }

    /**
     * Called during test whenever a new driver station packet arrives (about
     * every 1/50 of a second).
     */
    public void testPeriodic() {
        // Updates sensors & actuators on the LiveWindow
        LiveWindow.run();
    }

    /** Called after any of the other modes ends. */
    public void disabledInit() {
        SmartDashboard.putBoolean("Enabled", false);
        if(autonomouse != null) {
            autonomouse.cancel();
        }
        if(teleop != null) {
            teleop.cancel();
        }
        // Make sure the shooter doesn't start spinning again when the robot
        // re-enables.
        shooter.setSetpoint(0);
        shooter.setMotorValRaw(0);
    }
    /**
     * Called during disabled whenever a new driver station packet arrives
     * (about every 1/50 of a second). We only have it overridden so we don't
     * get "Override me!" messages.
     */
    public void disabledPeriodic() {}
}
