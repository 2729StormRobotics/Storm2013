/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.tables.ITable;
import storm2013.Robot;
import storm2013.utilities.NetworkTablePIDSource;

/**
 *
 * @author evan1026
 */
public class NetworkTableTurnRobot extends PIDCommand {

    public static final String THREE_POINT_KEY = "3pt Target X";
    public static final String THREE_POINT_FOUND_KEY = "3pt Target Found?";
    public static final String TWO_POINT_KEY = "2pt Target X";
    public static final String TWO_POINT_FOUND_KEY = "2pt Target Found?";
    
    private PIDController _controller;
    private PIDOutput _output = new PIDOutput() {
        public void pidWrite(double output) {
            usePIDOutput(output);
        }
    };
    private NetworkTablePIDSource _source;
    
    private ITable _table;
    
    private boolean targetFound;
    private String targetFoundKey;
    private boolean lastFoundValues[] = new boolean[5];
    
    
    public NetworkTableTurnRobot(ITable table, String key){
        super(0, 0, 0);
        requires(Robot.driveTrain);
        _source = new NetworkTablePIDSource(table, key);
        _controller = new PIDController(0, 0, 0, _source, _output);
        _table = table;
        
        if (key.equals(THREE_POINT_KEY)){
            targetFoundKey = THREE_POINT_FOUND_KEY;
        }
        else{
            targetFoundKey = TWO_POINT_FOUND_KEY;
        }
    }
    
    protected double returnPIDInput() {
        return _source.pidGet();
    }

    protected void usePIDOutput(double output) {
        Robot.driveTrain.tankDrive(output, -output);
    }

    protected void initialize() {
        _controller.enable();
        _controller.setSetpoint(0);
    }

    protected void execute() {
        for(int i = 0; i < lastFoundValues.length - 1; i++){
            lastFoundValues[i] = lastFoundValues[i + 1];
        }
        lastFoundValues[lastFoundValues.length - 1] = _table.getBoolean(targetFoundKey);
        
        boolean willEnd = true;
        for(int i = 0; i < lastFoundValues.length; i++){
            if (lastFoundValues[i]){
                willEnd = false;
                break;
            }
        }
        
        if (willEnd) end();
    }

    protected boolean isFinished() {
        return _controller.onTarget();
    }

    protected void end() {
        _controller.disable();
    }

    protected void interrupted() {
        end();
    }
    
}
