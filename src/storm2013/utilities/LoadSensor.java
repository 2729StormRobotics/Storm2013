package storm2013.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Provides the ability to easily interface with the load sensor.
 * @author Storm
 */
public class LoadSensor implements LiveWindowSendable{
    
    AnalogChannel loadSensor;
    
    ITable _table;
    
    //value is slope: x-axis: amps y-axis: volts (Dropbox/Storm2729/current-sensor-curve.xlsx)
    final double voltToAmpRatio = 39.46e-3;
    
    /**
     * 
     * @param channel The analog channel where the load sensor is plugged in.
     */
    public LoadSensor(int channel){
        loadSensor = new AnalogChannel(channel);
    }
    
    /**
     * Gets how many amps are flowing through the sensor. May be a bit inaccurate,
     * since it's calculated based on the voltage.
     * @return The value in amps
     */
    public double getAmps(){
        return loadSensor.getVoltage() / voltToAmpRatio;
    }
    
    /**
     * Gets how many volts the load sensor is reading.
     * @return The value in volts
     */
    public double getVolts(){
        return loadSensor.getVoltage();
    }
    
    /**
     * Puts the numbers into the network table
     */
    public void updateTable() {
        if(_table != null) {
            _table.putNumber("voltage", loadSensor.getVoltage());
            _table.putNumber("amperage", getAmps());
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

    /**
     * Sets up the network table for this to write to
     * @param table The network table for this to write to
     */
    public void initTable(ITable table) {
        _table = table;
        updateTable();
    }

    /**
     * Returns the network table this is writing to.
     * @return The table
     */
    public ITable getTable() {
        return _table;
    }

    /**
     * Returns the type to be used for smart dashboard. I can't say I fully know
     * what it does, but if someone does, they can change this documentation.
     * @return "Load Sensor"
     */
    public String getSmartDashboardType() {
        return "Load Sensor";
    }
}
