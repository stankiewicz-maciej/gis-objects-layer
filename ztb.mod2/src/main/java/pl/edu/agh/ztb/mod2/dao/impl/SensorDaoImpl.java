package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.SensorDao;
import pl.edu.agh.ztb.mod2.model.Sensor;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;

public class SensorDaoImpl implements SensorDao {
	
	private ConnectionFactory cm = ConnectionFactory.getInstance();

	@Override
	public Set<Sensor> getAllSensors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor getSensor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sensor getSensorsBySegmentCtrl(int segmentCtrlId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteSensor(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSensor(Sensor sensor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSensorData(int sensorId, Properties data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Properties getSensorData(int sensorId) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
