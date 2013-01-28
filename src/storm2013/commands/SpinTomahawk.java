/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author evan1026
 */
public class SpinTomahawk extends Command {

    static final int PORT_TOMAHAWK_MOTOR = 4;
    static final int PORT_TOMAHAWK_LIMIT_SWITCH = 0;
    
    private Jaguar _motor;
    private DigitalInput _limitSwitch;
    private boolean _hasChanged;
    private boolean _done;
    private double _speed;
    
    public SpinTomahawk(double speed){
        _motor = new Jaguar(PORT_TOMAHAWK_MOTOR);
        _limitSwitch = new DigitalInput(PORT_TOMAHAWK_LIMIT_SWITCH);
        _speed = speed;
    }
    
    protected void initialize() {
        _motor.set(_speed);
    }

    protected void execute() {
        if (!_hasChanged){
            if (_limitSwitch.get()) _hasChanged = true;
        }
        else{
            if (!_limitSwitch.get()){
                _motor.set(0);
                _done = true;
            }
        }
        
    }

    protected boolean isFinished() {
        return _done;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
