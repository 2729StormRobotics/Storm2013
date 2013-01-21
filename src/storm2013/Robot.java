package storm2013;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.*;
import storm2013.subsystems.DriveTrain;
import storm2013.subsystems.Shooter;

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
    
    Command   teleop;
    String[]  autonomiceNames;
    Command[] autonomice;
    SendableChooser chooser = new SendableChooser();
    Command   autonomouse;

    public void robotInit() {
        oi = new OI();
        driveTrain = new DriveTrain();
        shooter = new Shooter();
	
//	teleop = new DoNothing();
//        teleop = new ArcadeDrive();
    	teleop = new TestShooter();
        
        autonomiceNames = new String[]{"Do Nothing", "Dance!"};
        autonomice = new Command[]{new DoNothing(),new DonaldDance()};
        
        System.out.println(autonomice.length);
        for(int i=0;i<autonomice.length;++i) {
            chooser.addObject(autonomiceNames[i],autonomice[i]);
        }
        SmartDashboard.putData("Which Autonomouse?",chooser);
    }

    public void autonomousInit() {
        autonomouse = (Command)chooser.getSelected();
        if(autonomouse != null) {
            autonomouse.start();
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if(autonomouse != null) {
            autonomouse.cancel();
        }
        teleop.start();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }

    // Eliminates "Overload me!" messages
    public void disabledInit() {}
    public void disabledPeriodic() {}
}
