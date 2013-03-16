package storm2013.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/** Provides the ability to easily interface with the load sensor. */
public class LoadSensor implements LiveWindowSendable {
    // slope: x-axis: amps y-axis: volts (Dropbox/Storm2729/current-sensor-curve.xlsx)
    private final double voltToAmpRatio = 39.46e-3;
    
    private AnalogChannel loadSensor;
    private ITable _table;
    
    public LoadSensor(int channel){
        loadSensor = new AnalogChannel(channel);
    }
    
    /** Returns (approximate) amperage being drawn. */
    public double getAmps(){
        return loadSensor.getVoltage() / voltToAmpRatio;
    }
    
    /** Returns voltage drop being read. */
    public double getVolts(){
        return loadSensor.getVoltage();
    }
    
    /** Puts current data into the {@link ITable}. */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("voltage", loadSensor.getVoltage());
            _table.putNumber("amperage", getAmps());
        }
    }

    public void startLiveWindowMode() {}
    public void stopLiveWindowMode() {}

    /** Sets up an {@link ITable} for writing. */
    public void initTable(ITable table) {
        _table = table;
        updateTable();
    }

    /** Returns the currently used {@link ITable}. */
    public ITable getTable() {
        return _table;
    }

    /** We have a custom widget, so it gets a custom type. */
    public String getSmartDashboardType() {
        return "Load Sensor";
    }
}
