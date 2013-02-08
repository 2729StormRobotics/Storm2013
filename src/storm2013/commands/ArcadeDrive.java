package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.OI;
import storm2013.Robot;
import storm2013.subsystems.DriveTrain;

public class ArcadeDrive extends Command {
    DriveTrain driveTrain = Robot.driveTrain;
    OI oi = Robot.oi;
    
    public ArcadeDrive() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {}

    protected void execute() {
        double drive = oi.getDriveAxis(),
               turn  = oi.getTurnAxis();
        driveTrain.arcadeDrive(drive, turn);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
