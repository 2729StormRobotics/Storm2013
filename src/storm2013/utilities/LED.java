/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 *
 * @author Joe
 */
public class LED implements LiveWindowSendable {
    private PWM _led;
    private ITable _table;
    
    public LED(int port) {
        _led = new PWM(port);
    }
    
    // 0-255
    public void set(int value) {
        _led.setRaw(value);
    }
    
    public int get() {
        return _led.getRaw();
    }

    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", get()*2/255.0-1);
        }
    }

    public void startLiveWindowMode() {
        _led.setRaw(0);
        ITableListener table_listener = new ITableListener() {
            public void valueChanged(ITable itable, String key, Object value, boolean bln) {
                set(Math.min((int)((((Double) value).doubleValue()+1)*255.0/2),255));
            }
        };
        _table.addTableListener("Value", table_listener, true);
    }

    public void stopLiveWindowMode() {
    }

    public void initTable(ITable subtable) {
        _table = subtable;
        updateTable();
    }

    public ITable getTable() {
        return _table;
    }

    public String getSmartDashboardType() {
        return "Speed Controller";
    }
}
