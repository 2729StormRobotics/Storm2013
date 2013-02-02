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
public class NetworkTablePIDSource implements PIDSource {

    private ITable _table;
    private String _key;
    
    public NetworkTablePIDSource(ITable table, String key){
        _table = table;
        _key = key;
    }
    
    
    public double pidGet() {
        return _table.getNumber(_key);
    }
    
}
