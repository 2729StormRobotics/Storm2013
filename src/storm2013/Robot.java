package storm2013;

import storm2013.commands.autonomous.JustShoot;
import storm2013.commands.autonomous.ShootFromPyramidRight;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.*;
import storm2013.subsystems.DriveTrain;
import storm2013.subsystems.LEDStrip;
import storm2013.subsystems.Shooter;
import storm2013.subsystems.Tilter;
import storm2013.subsystems.Tomahawk;
import storm2013.subsystems.Vision;
import storm2013.utilities.LoadSensor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
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
    
    LoadSensor loadSensor;
    

    public void robotInit() {
        driveTrain = new DriveTrain();
        shooter    = new Shooter();
        tilter     = new Tilter();
        tomahawk   = new Tomahawk();
        ledStrip   = new LEDStrip();
        vision     = new Vision();
        oi         = new OI();
        ledStrip   = new LEDStrip();

        loadSensor = new LoadSensor(2);
        
        autonomiceNames = new String[]{"Do Nothing", "Shoot from pyramid right", "Just shoot","Cycle LEDs"};

        autonomice = new Command[]{new DoNothing(), new ShootFromPyramidRight(), new JustShoot(),new CycleLEDs()};

        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);

        LiveWindow.addSensor("Load Sensor", "Load Sensor 1", loadSensor);
//        SmartDashboard.putData("Shooter PID",shooter.getPIDController());
        
        // Send sensor info over to the dashboard
        new Command("Sensor feedback") {
            protected void initialize() {}
            protected void execute() {
                SmartDashboard.putNumber("Wheel Speed (RPM)", shooter.getSpeedRpm());
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

    public void autonomousInit() {
        if (teleop != null) {
            teleop.cancel();
        }
        autonomouse = (Command) chooser.getSelected();
        if (autonomouse != null) {
            autonomouse.start();
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomouse != null) {
            autonomouse.cancel();
        }
        if (teleop != null) {
            teleop.start();
        }
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }

    public void disabledInit() {
        if(autonomouse != null) {
            autonomouse.cancel();
        }
        if(teleop != null) {
            teleop.cancel();
        }
        shooter.setSetpoint(0);
        shooter.setMotorValRaw(0);
    }

    // Eliminates "Overload me!" messages
    public void disabledPeriodic() {}
}
