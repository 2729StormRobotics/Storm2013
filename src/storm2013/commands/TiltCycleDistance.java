package storm2013.commands;

import storm2013.Robot;
import storm2013.subsystems.Vision.Distance;

public class TiltCycleDistance extends TiltForDistance {
    private boolean _forward;
    public TiltCycleDistance(boolean forward,double power) {
        super(power, Distance.NEAR);
        _forward = forward;
    }

    protected void initialize() {
        _nextDistance();
        super.initialize();
    }
    
    protected void _nextDistance() {
        _setDistance(_forward ? Robot.vision.nextDistance() :
                               Robot.vision.prevDistance());
    }
}
