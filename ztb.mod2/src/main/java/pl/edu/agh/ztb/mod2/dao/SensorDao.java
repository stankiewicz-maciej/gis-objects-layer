package pl.edu.agh.ztb.mod2.dao;

import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.model.Sensor;

public interface SensorDao {

	Set<Sensor> getAllSensors() throws DataObjectDaoException;
	Sensor getSensor(int id) throws DataObjectDaoException;
	Sensor getSensorByLocation(int locationId) throws DataObjectDaoException;
	Set<Sensor> getSensorsBySegmentCtrl(int segmentCtrlId) throws DataObjectDaoException;
	int deleteSensor(int id) throws DataObjectDaoException;
	int insertSensor(Sensor sensor) throws DataObjectDaoException;
	int updateSensor(Sensor sensor) throws DataObjectDaoException;
	int updateSensorData(int sensorId, Properties data) throws DataObjectDaoException;
	Properties getSensorData(int sensorId)throws DataObjectDaoException;
}
