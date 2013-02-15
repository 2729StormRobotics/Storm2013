package storm2013.utilities;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * A class that makes it easier to deal with LED's.
 * @author Joe
 */
public class LED implements LiveWindowSendable {
    private PWM _led;
    private ITable _table;
    
    /**
     * 
     * @param port The port the LED is plugged into
     */
    public LED(int port) {
        _led = new PWM(port);
    }
    
    /**
     * Sets the value for the LED.
     * @param value The value to set between 0-255.
     */
    public void set(int value) {
        _led.setRaw(value);
    }
    
    /**
     * Gets the value being output to the LED
     * @return The value being output
     */
    public int get() {
        return _led.getRaw();
    }

    /**
     * Puts the LED output value into the network table.
     */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", get()*2/255.0-1);
        }
    }

    /**
     * Sets stuff up so this all works with LiveWindow.
     */
    public void startLiveWindowMode() {
        _led.setRaw(0);
        ITableListener table_listener = new ITableListener() {
            public void valueChanged(ITable itable, String key, Object value, boolean bln) {
                set(Math.min((int)((((Double) value).doubleValue()+1)*255.0/2),255));
            }
        };
        _table.addTableListener("Value", table_listener, true);
    }

    /**
     * Doesn't do anything.
     */
    public void stopLiveWindowMode() {
    }

    /**
     * Initializes the table the LED writes to.
     * @param subtable The table to write to
     */
    public void initTable(ITable subtable) {
        _table = subtable;
        updateTable();
    }

    /**
     * Gets the table this is writing to.
     * @return That table
     */
    public ITable getTable() {
        return _table;
    }

    /**
     * Returns the type to be used for smart dashboard. I can't say I fully know
     * what it does, but if someone does, they can change this documentation.
     * @return "Speed Controller"
     */
    public String getSmartDashboardType() {
        return "Speed Controller";
    }
}
