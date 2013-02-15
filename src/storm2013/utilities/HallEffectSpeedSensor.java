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

    /**
     * 
     * @param port The port for the hall effect sensor's digital input
     */
    public HallEffectSpeedSensor(int port) {
        _hallEffect = new DigitalInput(port);
        // TODO: Try this as a replacement for the rest of the counter initialization
        _counter = new Counter(_hallEffect);
//        _counter = new Counter(CounterBase.EncodingType.k1X, //Count only rising edge of digital signal
//                               _hallEffect,
//                               _hallEffect,
//                               false); //inverted
//
//        _counter.clearDownSource();
//        _counter.setUpSourceEdge(true, false); //TODO Check without this
        _counter.start();
    }
    
    /**
     * Sets the minimum speed that the sensor will pick up in RPM. This is to avoid
     * the speed returning NaN because the timer returns a time of infinity.
     * @param speedRpm The min speed
     */
    public void setMinSpeedRpm(double speedRpm) {
        _counter.setMaxPeriod(60/speedRpm);
    }
    
    /**
     * Gets the speed in RPM
     * @return the speed... in RPM
     */
    public double getSpeedRpm() {
        return 60/_counter.getPeriod();
    }

    ITable _table;
    
    /**
     * Initializes the table that the sensor puts data to.
     * @param table The table it's gonna put data to
     */
    public void initTable(ITable table) {
        _table = table;
        updateTable();
    }

    /**
     * Returns the table the sensor writes to
     * @return the aforementioned table
     */
    public ITable getTable() {
        return _table;
    }

    /**
     * Returns the type to be used for smart dashboard. I can't say I fully know
     * what it does, but if someone does, they can change this documentation.
     * @return "Analog Input"
     */
    public String getSmartDashboardType() {
        return "Analog Input";
    }

    /**
     * Puts the RPM into the table
     */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", getSpeedRpm());
        }
    }

    /**
     * LiveWindow wants it. I don't really know what it is supposed to do. It does
     * nothing anyway.
     */
    public void startLiveWindowMode() {}
    
    /**
     * LiveWindow wants it. I don't really know what it is supposed to do. It does
     * nothing anyway.
     */
    public void stopLiveWindowMode() {}

}
