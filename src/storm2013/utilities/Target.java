package storm2013.utilities;

/** Enumeration of different targets and axes for target tracking. */
public class Target{
    public final static Target TwoPT = new Target("2pt Target ");
    public final static Target ThreePT = new Target("3pt Target ");
    public final static Target FivePT = new Target("5pt Target ");
    
    /** Different types of angles (X = horizontal,Y = vertical). */
    public static class Axis {
        public final static Axis X = new Axis("X Angle");
        public final static Axis Y = new Axis("Y Angle");
        
        private String _value;
        private Axis(String value) {
            _value = value;
        }
        
        public String getValue() {
            return _value;
        }
    }

    private String _key;
    private Target(String key){
        _key = key;
    }
    
    /** Returns the SmartDashboard key for a given Axis. */
    public String getAxisKey(Axis axis) {
        return _key + axis.getValue();
    }
    
    /** Returns the SmartDashboard key for the X axis. */
    public String getXKey(){
        return getAxisKey(Axis.X);
    }
    
    /** Returns the SmartDashboard key for the Y axis. */
    public String getYKey(){
        return getAxisKey(Axis.Y);
    }
    
    /** Returns the SmartDashboard key for whether a target has been found. */
    public String getFoundKey(){
        return _key + "Found?";
    }
}
