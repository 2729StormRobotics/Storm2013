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
    public static Target TwoPT = new Target("2pt Target ");
    public static Target ThreePT = new Target("3pt Target ");
    public static Target FivePT = new Target("5pt Target ");

    private String _key;
    private Target(String key){
        _key = key;
    }
    public String getXKey(){
        return _key + "X Angle";
    }
    public String getYKey(){
        return _key + "Y Angle";
    }
    public String getFoundKey(){
        return _key + "Found?";
    }
}
