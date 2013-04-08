package storm2013.commands;

import storm2013.Robot;
import storm2013.subsystems.Vision.Distance;

public class TiltSetDistance extends TiltForDistance {

    public TiltSetDistance(double power, Distance distance) {
        super(power, distance);
    }

    protected void initialize() {
        Robot.vision.setDistance(_getDistance());
        super.initialize();
    }
}
