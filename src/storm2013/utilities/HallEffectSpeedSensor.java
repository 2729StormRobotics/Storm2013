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
    
    public void setMinSpeedRpm(double speedRpm) {
        _counter.setMaxPeriod(60/speedRpm);
    }
    
    public double getSpeedRpm() {
        return 60/_counter.getPeriod();
    }

    ITable _table;
    public void initTable(ITable table) {
        _table = table;
        updateTable();
    }

    public ITable getTable() {
        return _table;
    }

    public String getSmartDashboardType() {
        return "Analog Input";
    }

    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", getSpeedRpm());
        }
    }

    public void startLiveWindowMode() {}
    public void stopLiveWindowMode() {}

}
