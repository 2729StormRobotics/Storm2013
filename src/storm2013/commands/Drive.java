package storm2013.commands;

import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 *
 * @author Storm
 */
public class Drive extends Command {
    private double _power,_turn;
    
    public Drive(double power,double turn) {
        requires(Robot.driveTrain);
        _power = power;
        _turn = turn;
    }

    protected void initialize() {
    }

    protected void execute() {
        Robot.driveTrain.arcadeDrive(_power, _turn);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.driveTrain.arcadeDrive(0, 0);
    }

    protected void interrupted() {
        end();
    }
}
