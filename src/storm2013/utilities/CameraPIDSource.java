/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author evan1026
 */
public class CameraPIDSource implements PIDSource {

    private ITable _table;
    private String _key;

    public CameraPIDSource(ITable table, Target target){
        _table = table;
        _key = target.getXKey();
    }
    public double pidGet() {
        return _table.getNumber(_key);
    }


    public static class Target{
        public static Target TwoPT = new Target("2pt Target ");
        public static Target ThreePT = new Target("3pt Target ");
        public static Target FivePT = new Target("5pt Target ");

        private String _key;
        private Target(String key){
            _key = key;
        }
        public String getXKey(){
            return _key + "X";
        }
        public String getYKey(){
            return _key + "Y";
        }
        public String getFoundKey(){
            return _key + "Found?";
        }
    }
}
