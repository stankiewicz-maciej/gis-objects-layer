package pl.edu.agh.ztb.mod2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.dao.SensorDao;
import pl.edu.agh.ztb.mod2.model.Sensor;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class SensorDaoImpl implements SensorDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();

	@Override
	public Set<Sensor> getAllSensors() throws DataObjectDaoException {
		Set<Sensor> set = new HashSet<Sensor>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("sensor.get.all"));
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new Sensor(rs.getInt("id"), rs.getInt("segment_ctrl_id"), rs.getInt("location_id"),	rs.getInt("sensor_type_id")));
			}
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting all sensors", e);
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public Sensor getSensor(int id) throws DataObjectDaoException {
		Sensor c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("sensor.get.id"));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				c = new Sensor(rs.getInt("id"), rs.getInt("segment_ctrl_id"),
						rs.getInt("location_id"), rs.getInt("sensor_type_id"));
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting sensor", e);
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	@Override
	public Sensor getSensorByLocation(int locationId) throws DataObjectDaoException {
		Sensor c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("sensor.get.location_id"));
			ps.setInt(1, locationId);
			rs = ps.executeQuery();
			if (rs.next())
				c = new Sensor(rs.getInt("id"), rs.getInt("segment_ctrl_id"),
						rs.getInt("location_id"), rs.getInt("sensor_type_id"));
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting sensor", e);
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	@Override
	public Set<Sensor> getSensorsBySegmentCtrl(int segmentCtrlId) throws DataObjectDaoException {
		Set<Sensor> set = new HashSet<Sensor>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries
					.getProperty("sensor.get.segment_ctrl_id"));
			ps.setInt(1, segmentCtrlId);
			rs = ps.executeQuery();
			while (rs.next())
				set.add(new Sensor(rs.getInt("id"), rs
						.getInt("segment_ctrl_id"), rs.getInt("location_id"),
						rs.getInt("sensor_type_id")));
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting sensor", e);
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public int deleteSensor(int id) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("sensor.delete"));
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during deleting sensor", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int insertSensor(Sensor sensor) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(queries.getProperty("sensor.insert"));
			ps.setInt(1, sensor.getLocation_id());
			ps.setInt(2, sensor.getSegment_ctrl_id());
			ps.setInt(3, sensor.getSensor_type_id());
			int rs = ps.executeUpdate();
			if (sensor.getSensor_data() != null) {
				Properties data = sensor.getSensor_data();
				for (String key : data.stringPropertyNames()) {
					PreparedStatement dataPS = conn.prepareStatement(queries
							.getProperty("sensor.data.insert"));
					dataPS.setInt(1, sensor.getId());
					dataPS.setString(2, key);
					dataPS.setString(3, data.getProperty(key));
				}
			} else {
				conn.commit();
			}
			return rs;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DataObjectDaoException("Error during inserting sensor", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int updateSensor(Sensor sensor) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("sensor.update"));
			ps.setInt(1, sensor.getLocation_id());
			ps.setInt(2, sensor.getSegment_ctrl_id());
			ps.setInt(3, sensor.getSensor_type_id());
			ps.setInt(4, sensor.getId());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during updating sensor", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int updateSensorData(int sensorId, Properties data) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			int numberOfUpdates = 0;
			for (String key : data.stringPropertyNames()) {
				PreparedStatement dataPS = conn.prepareStatement(queries
						.getProperty("sensor.data.update.exist"));
				dataPS.setString(1, data.getProperty(key));
				dataPS.setInt(2, sensorId);
				dataPS.setString(3, key);
				int rs = dataPS.executeUpdate();
				if (rs < 1) {
					dataPS = conn.prepareStatement(queries
							.getProperty("sensor.data.update.not_exist"));
					dataPS.setInt(1, sensorId);
					dataPS.setString(2, key);
					dataPS.setString(3, data.getProperty(key));
					rs = dataPS.executeUpdate();
				}
				numberOfUpdates = numberOfUpdates + rs;
			}
			return numberOfUpdates;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during updating sensor data", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public Properties getSensorData(int sensorId) throws DataObjectDaoException {
		Properties data = new Properties();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries
					.getProperty("sensor.data.select.id"));
			ps.setInt(1, sensorId);
			rs = ps.executeQuery();
			while (rs.next()) {
				data.setProperty(rs.getString("key"), rs.getString("value"));
			}
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting sensor data", e);
		} finally {
			close(rs, ps, conn);
		}
		return data;
	}

	private void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (ps != null && !ps.isClosed()) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(PreparedStatement ps, Connection conn) {
		try {
			if (ps != null && !ps.isClosed()) ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
