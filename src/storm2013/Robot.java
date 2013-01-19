package storm2013;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.Autonomous;
import storm2013.commands.Teleop;
import storm2013.commands.TestShooter;
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
    Command[] autonomice = new Command[0];
    SendableChooser chooser = new SendableChooser();
    Command   autonomouse;
    
    
    

    public void robotInit() {
        oi = new OI();
        //driveTrain = new DriveTrain();
        
	shooter = new Shooter();
	
        teleop = new TestShooter();
        autonomice = new Command[]{ new Autonomous() };
//        for(int i=0;i<autonomice.length;++i) {
//            chooser.addObject(autonomiceNames[i],autonomice[i]);
//        }
	SmartDashboard.putData(teleop);
    }

    public void autonomousInit() {
        autonomouse = (Command)chooser.getSelected();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
//        autonomouse.cancel();
	teleop.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
