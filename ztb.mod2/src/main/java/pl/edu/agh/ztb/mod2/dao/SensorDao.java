package pl.edu.agh.ztb.mod2.dao;

import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.model.Sensor;

public interface SensorDao {

	Set<Sensor> getAllSensors();
	Sensor getSensor(int id);
	Set<Sensor> getSensorsBySegmentCtrl(int segmentCtrlId);
	int deleteSensor(int id);
	int insertSensor(Sensor sensor);
	int updateSensor(Sensor sensor);
	int updateSensorData(int sensorId, Properties data);
	Properties getSensorData(int sensorId);
}
