package storm2013.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import storm2013.RobotMap;

/**
 *
 * @author Joe
 */
public class Tilter extends Subsystem {
    private static double UP_SPEED   = -0.6,
                          DOWN_SPEED = 0.6;
    
    private Jaguar _motor = new Jaguar(RobotMap.PORT_MOTOR_TILTER);

    public Tilter() {
        LiveWindow.addActuator("Tilter", "Motor", _motor);
    }

    public void initDefaultCommand() {}
    
    public void moveUp() {
        _motor.set(UP_SPEED);
    }
    public void stop() {
        _motor.set(0);
    }
    public void moveDown() {
        _motor.set(DOWN_SPEED);
    }
}
