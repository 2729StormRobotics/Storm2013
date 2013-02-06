package storm2013;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.*;
import storm2013.subsystems.DriveTrain;
import storm2013.subsystems.Shooter;
import storm2013.subsystems.Tilter;
import storm2013.utilities.HallEffectSpeedSensor;
import storm2013.utilities.LoadSensor;
import storm2013.utilities.Target;

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
    
    Command teleop;
    String[] autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command autonomouse;
    
    Jaguar testJag;
    LoadSensor loadSensor;

    public void robotInit() {
        driveTrain = new DriveTrain();
        shooter = new Shooter();
        tilter  = new Tilter();
        oi = new OI();

        loadSensor = new LoadSensor(1);
        testJag = new Jaguar(6);


    	teleop = new TestShooter();

        autonomiceNames = new String[]{"Do Nothing", "Dance!", "DriveStuff"};

        autonomice = new Command[]{new DoNothing(), new DonaldDance(), new DriveStuff()};

        System.out.println(autonomice.length);
        for (int i = 0; i < autonomice.length; ++i) {
            chooser.addObject(autonomiceNames[i], autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?", chooser);

//	SmartDashboard.putData(Scheduler.getInstance());

        LiveWindow.addSensor("Load Sensor", "Load Sensor 1", loadSensor);
        
        shooter.setAcceleratorEnabled(true);
        
        CameraPIDTurn turnCommand = new CameraPIDTurn(NetworkTable.getTable("SmartDashboard"), Target.FivePT, 2.0e6);
        SmartDashboard.putData(turnCommand);
        SmartDashboard.putData("Turn PID",turnCommand.getPIDController());
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
