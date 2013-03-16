package storm2013.utilities;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * This uses a digital signal from a hall effect sensor to calculate the speed
 * of a wheel.
 * @author Joe
 */
public class HallEffectSpeedSensor implements LiveWindowSendable {
    private DigitalInput _hallEffect;
    private Counter _counter;

    public HallEffectSpeedSensor(int port) {
        _hallEffect = new DigitalInput(port);
        _counter = new Counter(_hallEffect);
        _counter.start();
    }
    
    /**
     * Sets the minimum speed that the sensor will pick up in RPM. Below this
     * speed, _counter.getPeriod() will return infinity, and the speed will be 0.
     */
    public void setMinSpeedRpm(double speedRpm) {
        _counter.setMaxPeriod(60/speedRpm);
    }
    
    /** Reads the speed in RPM */
    public double getSpeedRpm() {
        return 60/_counter.getPeriod();
    }

    ITable _table;
    
    /** Initializes the table that the sensor puts data into. */
    public void initTable(ITable table) {
        _table = table;
        updateTable();
    }

    /** Returns the table the sensor writes to. */
    public ITable getTable() {
        return _table;
    }

    /**
     * Returns the type to be used for smart dashboard. It returns "Analog
     * Input" because that type allows the output this sensor needs. We could
     * make a custom widget, but that's a pain.
     */
    public String getSmartDashboardType() {
        return "Analog Input";
    }

    /** Sends the speed to SmartDashboard. */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", getSpeedRpm());
        }
    }

    public void startLiveWindowMode() {}
    public void stopLiveWindowMode() {}
}
