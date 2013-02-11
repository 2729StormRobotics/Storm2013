/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

/**
 *
 * @author evan1026
 */
public class Target{
    public final static Target TwoPT = new Target("2pt Target ");
    public final static Target ThreePT = new Target("3pt Target ");
    public final static Target FivePT = new Target("5pt Target ");
    
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
    public String getAxisKey(Axis axis) {
        return _key + axis.getValue();
    }
    public String getXKey(){
        return getAxisKey(Axis.X);
    }
    public String getYKey(){
        return getAxisKey(Axis.Y);
    }
    public String getFoundKey(){
        return _key + "Found?";
    }
}
