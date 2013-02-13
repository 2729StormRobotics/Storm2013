package storm2013;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.*;
import storm2013.subsystems.DriveTrain;
import storm2013.subsystems.Shooter;
import storm2013.subsystems.Tilter;
import storm2013.subsystems.Tomahawk;
import storm2013.utilities.LoadSensor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public static OI oi;
    public static DriveTrain driveTrain;
    public static Shooter shooter;
    public static Tilter tilter;
    public static Tomahawk tomahawk;
    
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
    Jaguar testJag;
    LoadSensor loadSensor;

    public void robotInit() {
        driveTrain = new DriveTrain();
        shooter    = new Shooter();
        tilter     = new Tilter();
        tomahawk   = new Tomahawk();
        oi         = new OI();

        loadSensor = new LoadSensor(2);
        testJag = new Jaguar(6);

        autonomiceNames = new String[]{"Do Nothing", "DriveStuff"};

        autonomice = new Command[]{new DoNothing(), new DriveStuff()};

        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);

//	SmartDashboard.putData(Scheduler.getInstance());

        LiveWindow.addSensor("Load Sensor", "Load Sensor 1", loadSensor);
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
        SmartDashboard.putData("Load Sensor", loadSensor);
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
        SmartDashboard.putNumber("Tilter angle", tilter.getAngle());
        SmartDashboard.putNumber("Wheel Speed (RPM)", shooter.getSpeedRpm());
        SmartDashboard.putNumber("Gyro angle", driveTrain.getGyroAngle());
    }

    public void testPeriodic() {
        LiveWindow.run();
    }

    // Eliminates "Overload me!" messages
    public void disabledInit() {
        if(autonomouse != null) {
            autonomouse.cancel();
        }
        if(teleop != null) {
            teleop.cancel();
        }
    }

    public void disabledPeriodic() {
    }
}
