package storm2013.utilities;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/** Controls an LED with a {@link PWM} signal. */
public class LED implements LiveWindowSendable {
    private PWM _led;
    private ITable _table;
    
    public LED(int port) {
        _led = new PWM(port);
    }
    
    /** Sets the {@link PWM} output to the LED [0,255]. */
    public void set(int value) {
        _led.setRaw(value);
    }
    
    /** Reads the current {@link PWM} output. */
    public int get() {
        return _led.getRaw();
    }

    /**
     * Converts the [0,255] {@link PWM} signal to [-1,1] so that it can be
     * controlled on {@link LiveWindow} like a {@link Victor} or {@link Jaguar}.
     */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", get()*2/255.0-1);
        }
    }
    
    ITableListener _listener = new ITableListener() {
        public void valueChanged(ITable itable, String key, Object value, boolean bln) {
            set(Math.min((int)((((Double) value).doubleValue()+1)*255.0/2),255));
        }
    };

    /** Readies to listen for changes from {@link LiveWindow}. */
    public void startLiveWindowMode() {
        _led.setRaw(0);
        _table.addTableListener("Value", _listener,
                                true); // immediateNotify
    }

    /** Ends {@link LiveWindow} listening. */
    public void stopLiveWindowMode() {
        _table.removeTableListener(_listener);
    }

    /** Initializes the table for {@link SmartDashboard}/{@link LiveWindow} */
    public void initTable(ITable subtable) {
        _table = subtable;
        updateTable();
    }

    /** Returns the table being written to. */
    public ITable getTable() {
        return _table;
    }

    /**
     * Tells {@link SmartDashboard} that this is a speed controller.
     */
    public String getSmartDashboardType() {
        return "Speed Controller";
    }
}
