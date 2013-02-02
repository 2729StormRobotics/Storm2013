/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storm2013.utilities;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 * @author Storm
 */


public class LoadSensor implements LiveWindowSendable{
    
    AnalogChannel loadSensor;
    
    ITable _table;
    
    //value is slope: x-axis: amps y-axis: volts (Dropbox/Storm2729/current-sensor-curve.xlsx)
    final double voltToAmpRatio = 39.46e-3;
    
    public LoadSensor(int channel){
	loadSensor = new AnalogChannel(channel);
    }
    
    public double getAmps(){
	return loadSensor.getVoltage() / voltToAmpRatio;
    }
    public double getVolts(){
	return loadSensor.getVoltage();
    }
    
    public void updateTable() {
	if(_table != null) {
            _table.putNumber("voltage", loadSensor.getVoltage());
	    _table.putNumber("amperage", getAmps());
	}
    }

    public void startLiveWindowMode() {}

    public void stopLiveWindowMode() {}

    public void initTable(ITable table) {
	_table = table;
	updateTable();
    }

    public ITable getTable() {
	return _table;
    }

    public String getSmartDashboardType() {
	return "LoadSensorType";
    }
    
}
