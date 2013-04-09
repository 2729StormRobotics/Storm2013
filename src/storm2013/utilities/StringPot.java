package storm2013.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class StringPot implements LiveWindowSendable {
    public final static double VAL_MAX_SAFE   = 3.30,
                               VAL_NEAR       = 3.00,
                               VAL_CENTER     = 2.67,
                               VAL_OPPAUTO    = 2.25,
                               VAL_FEEDER     = 1.81;
    
    private AnalogChannel _channel;
    
    public StringPot(int channelNum) {
        _channel = new AnalogChannel(channelNum);
    }
    
    public double get() {
        double val = _channel.getAverageVoltage();
        return val > 0.05 ? val : 0;
    }
    
    private ITable _table = null;

    public void updateTable() {
        if(_table != null) {
            _table.putNumber("Value", get());
        }
    }
    public void startLiveWindowMode() {}
    public void stopLiveWindowMode() {}

    public void initTable(ITable subtable) {
        _table = subtable;
        updateTable();
    }

    public ITable getTable() {
        return _table;
    }

    public String getSmartDashboardType() {
        return "Analog Input";
    }
}
