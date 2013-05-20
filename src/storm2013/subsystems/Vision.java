package storm2013.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import storm2013.commands.StringPotTilt;
import storm2013.commands.TiltForDistance;

/**
 * Subsystem providing an interface with StormCV to control the crosshair
 * location.
 */
public class Vision extends Subsystem {
    /**
     * These are the distances we've calibrated crosshair locations for.
     */
    public static final class Distance {
        /** From the back of the pyramid forward. */
        public static final Distance NEAR          = new Distance("Near");
        /** Between the auto lines (at an angle). */
        public static final Distance CENTER        = new Distance("Center line");
        /** At the opponent auto line (at an angle). */
        public static final Distance OPPONENT_AUTO = new Distance("Opponent Auto");
        /** From the feeder station. */
        public static final Distance FEEDER        = new Distance("Feeder");
        
        private String _value;
        
        private Distance(String value) {
            _value = value;
        }
        
        public String getValue() {
            return _value;
        }
    }
    
    private static final Distance[] _distances = {
        Distance.NEAR,
        Distance.CENTER,
//        Distance.OPPONENT_AUTO,
        Distance.FEEDER
    };
    
    private int _currIndex = 0;
    
    public Vision() {
        _setDistance(0);
    }
    

    protected void initDefaultCommand() {}
    
    /** Set distance (manually, no cycling). */
    public void setDistance(Distance distance) {
        for(int i=0;i<_distances.length;++i) {
            if(_distances[i] == distance) {
                _setDistance(i);
                return;
            }
        }
    }
    
    private void _setDistance(int index) {
        index = index%_distances.length;
        if(index < 0) {
            index += _distances.length;
        }
        _currIndex = index;
        SmartDashboard.putString("Distance", _distances[index].getValue());
    }
    
    /** Cycle through distances forward (near -> feeder). */
    public Distance nextDistance() {
        _setDistance(_currIndex+1);
        return _distances[_currIndex];
    }
    
    /** Cycle through distances backward (feeder -> near). */
    public Distance prevDistance() {
        _setDistance(_currIndex-1);
        return _distances[_currIndex];
    }

    public Distance getCurrDistance() {
        return _distances[_currIndex];
    }
}
