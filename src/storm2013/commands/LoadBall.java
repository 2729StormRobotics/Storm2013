package storm2013.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import storm2013.RobotMap;

/**
 * Moves a ball up the back to get it ready to be fired. All this does is create 
 * a new {@link ControlLoader}, then it an heros.
 * @author evan1026
 */
public class LoadBall extends Command {

    private DigitalInput ballLoadedSensor;
    private ControlLoader controlLoader;
    
    /**
     * Creates a new instance of the command.
     */
    public LoadBall(){
        ballLoadedSensor = new DigitalInput(RobotMap.PORT_BREAKBEAM_BALL_READY);
        controlLoader = new ControlLoader(true);
    }
    
    /**
     * {@inheritDoc}
     */
    protected void initialize() {
    }

    /**
     * {@inheritDoc}
     */
    protected void execute() {
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    protected boolean isFinished() {
        return !ballLoadedSensor.get();
    }

    /**
     * {@inheritDoc}
     */
    protected void end() {
        controlLoader.end();
    }
    
   /**
    * {@inheritDoc}
    */
    protected void interrupted() {
        end();
    }
    
}
