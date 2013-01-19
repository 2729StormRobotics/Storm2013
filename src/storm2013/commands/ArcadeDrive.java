package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
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
        driveTrain.arcadeDrive(oi.getDriveAxis(), oi.getTurnAxis());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
