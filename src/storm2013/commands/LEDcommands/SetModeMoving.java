package storm2013.commands.LEDcommands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.Robot;

/**
 * Turns lights between white and off, in a sinusoid, with frequency
 * proportional to speed.
 */
public class SetModeMoving extends Command {
    private static final double PERIOD_SCALAR = 0.1;
    
    private Timer _timer = new Timer();
    private double _val = 0;
    
    public SetModeMoving(){
        requires(Robot.ledStrip);
    }
    
    public void execute() {
        Alliance alliance = DriverStation.getInstance().getAlliance();
        double r = 0,g = 0,b = 0;
        if(alliance.value == Alliance.kRed_val) {
            r = 1;
            g = b = 0;
        } else if(alliance.value == Alliance.kBlue_val) {
            b = 1;
            r = g = 0;
        } else {
            r = g = b = 1;
        }
        double rate = 2*Math.PI*(Math.abs(Robot.driveTrain.getLeftSpeed()) + Math.abs(Robot.driveTrain.getRightSpeed())) / 2;
        if(rate == 0) {
            _val = Math.PI/2;
        } else {
            _val += rate*_timer.get()/PERIOD_SCALAR;
            _val %= Math.PI*2;
        }
        _timer.reset();
        double scalar = (Math.sin(_val)+1)/2;
        if(scalar <= 0.25) {
            scalar = 0;
        } else {
            scalar = (scalar-0.25)/0.75;
        }
        double color = scalar*255;
        Robot.ledStrip.setColor((int)(r*color),(int)(g*color),(int)(b*color));
    }
    protected void initialize() {
        _timer.reset();
        _timer.start();
    }
    protected boolean isFinished() {
        return false;
    }
    protected void end() {
        _timer.stop();
    }
    protected void interrupted() {
        end();
    }
}
