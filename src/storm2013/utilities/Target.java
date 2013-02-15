package storm2013.utilities;

/**
 * Holds static values to make it easier to get network table values for targets
 * found by the camera.
 * @author evan1026
 */
public class Target{
    public final static Target TwoPT = new Target("2pt Target ");
    public final static Target ThreePT = new Target("3pt Target ");
    public final static Target FivePT = new Target("5pt Target ");
    
    /**
     * Used to Work with finding the angles for the targets.
     */
    public static class Axis {
        public final static Axis X = new Axis("X Angle");
        public final static Axis Y = new Axis("Y Angle");
        
        private String _value;
        private Axis(String value) {
            _value = value;
        }
        
        /**
         * Gets the value to use for that specific axis.
         * @return Read above
         */
        public String getValue() {
            return _value;
        }
    }

    private String _key;
    private Target(String key){
        _key = key;
    }
    
    /**
     * Returns the value to get the angle for a specified axis.
     * @param axis The axis to find the angle along.
     * @return The full key to use for the network table.
     */
    public String getAxisKey(Axis axis) {
        return _key + axis.getValue();
    }
    
    /**
     * Gets the key along the x axis for the target.
     * @return That key
     */
    public String getXKey(){
        return getAxisKey(Axis.X);
    }
    
    /**
     * Gets the key along the y axis for the target.
     * @return That key
     */
    public String getYKey(){
        return getAxisKey(Axis.Y);
    }
    
    /**
     * Gets the key that allows you to find out if the target has been found.
     * @return That key
     */
    public String getFoundKey(){
        return _key + "Found?";
    }
}
