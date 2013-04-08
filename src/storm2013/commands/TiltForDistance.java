package storm2013.commands;

import storm2013.Robot;
import storm2013.subsystems.Vision.Distance;
import storm2013.utilities.StringPot;

public class TiltForDistance extends StringPotTilt {
    private static double _targetFromDistance(Distance distance) {
        if(distance == Distance.NEAR) {
            return StringPot.VAL_NEAR;
        } else if(distance == Distance.CENTER) {
            return StringPot.VAL_CENTER;
        } else if(distance == Distance.OPPONENT_AUTO) {
            return StringPot.VAL_OPPAUTO;
        } else if(distance == Distance.FEEDER) {
            return StringPot.VAL_FEEDER;
        }
        return -1;
    }
    private Distance _distance;
    public TiltForDistance(double power,Distance distance) {
        super(power, _targetFromDistance(distance));
        _distance = distance;
    }
    
    protected Distance _getDistance() {
        return _distance;
    }
    protected void _setDistance(Distance distance) {
        _distance = distance;
        _setTarget(_targetFromDistance(distance));
    }

    protected void initialize() {
        super.initialize();
    }

    protected boolean isFinished() {
        return Robot.oi.getTilterAxis() != 0 || super.isFinished();
    }
}
