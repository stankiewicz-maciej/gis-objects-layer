package pl.edu.agh.ztb.mod2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.DriverDao;
import pl.edu.agh.ztb.mod2.model.Driver;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class DriverDaoImpl implements DriverDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();
	
	@Override
	public Set<Driver> getAllDrivers() {
		Set<Driver> set = new HashSet<Driver>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("driver.get.all"));
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new Driver(rs.getInt("id"), rs.getInt("fixture_id"), rs.getString("temperature"), rs.getString("connection_quality"), rs.getTimestamp("system_time"), rs.getString("power_usage"), rs.getString("voltage"), rs.getString("current"), rs.getString("power"), rs.getString("cos_value"), rs.getString("zigbee_address"), rs.getString("firmware"), rs.getString("serial_number"), rs.getString("product_type"), rs.getTimestamp("deployment_date"), rs.getString("net_state"), rs.getString("data_acceptance_state"), rs.getString("parametrization_state"), rs.getString("data_searching_state")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public Driver getDriver(int id) {
		Driver c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("driver.get.id"));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				c = new Driver(rs.getInt("id"), rs.getInt("fixture_id"), rs.getString("temperature"), rs.getString("connection_quality"), rs.getTimestamp("system_time"), rs.getString("power_usage"), rs.getString("voltage"), rs.getString("current"), rs.getString("power"), rs.getString("cos_value"), rs.getString("zigbee_address"), rs.getString("firmware"), rs.getString("serial_number"), rs.getString("product_type"), rs.getTimestamp("deployment_date"), rs.getString("net_state"), rs.getString("data_acceptance_state"), rs.getString("parametrization_state"), rs.getString("data_searching_state"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	@Override
	public Driver getDriverByFixture(int fixtureId) {
		Driver c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("driver.get.fixtureid"));
			ps.setInt(1, fixtureId);
			rs = ps.executeQuery();
			while (rs.next()) 
				c = new Driver(rs.getInt("id"), rs.getInt("fixture_id"), rs.getString("temperature"), rs.getString("connection_quality"), rs.getTimestamp("system_time"), rs.getString("power_usage"), rs.getString("voltage"), rs.getString("current"), rs.getString("power"), rs.getString("cos_value"), rs.getString("zigbee_address"), rs.getString("firmware"), rs.getString("serial_number"), rs.getString("product_type"), rs.getTimestamp("deployment_date"), rs.getString("net_state"), rs.getString("data_acceptance_state"), rs.getString("parametrization_state"), rs.getString("data_searching_state"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	@Override
	public int deleteDriver(int id) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("driver.delete"));
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int insertDriver(Driver driver) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("driver.insert"));
			ps.setInt(1, driver.getFixtureId());
			ps.setString(2, driver.getTemperature());
			ps.setString(3, driver.getConnectionQuality());
			ps.setTimestamp(4, driver.getSystemTime());
			ps.setString(5, driver.getPowerUsage());
			ps.setString(6, driver.getVoltage());
			ps.setString(7, driver.getCurrent());
			ps.setString(8, driver.getPower());
			ps.setString(9, driver.getCosValue());
			ps.setString(10, driver.getZigbeeAddress());
			ps.setString(11, driver.getFirmware());
			ps.setString(12, driver.getSerialNumber());
			ps.setString(13, driver.getProductType());
			ps.setTimestamp(14, driver.getDeploymentDate());
			ps.setString(15, driver.getNetState());
			ps.setString(16, driver.getDataAcceptanceState());
			ps.setString(17, driver.getParametrizationState());
			ps.setString(18, driver.getDataSearchingState());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int updateDriver(Driver driver) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("driver.update"));
			ps.setInt(1, driver.getFixtureId());
			ps.setString(2, driver.getTemperature());
			ps.setString(3, driver.getConnectionQuality());
			ps.setTimestamp(4, driver.getSystemTime());
			ps.setString(5, driver.getPowerUsage());
			ps.setString(6, driver.getVoltage());
			ps.setString(7, driver.getCurrent());
			ps.setString(8, driver.getPower());
			ps.setString(9, driver.getCosValue());
			ps.setString(10, driver.getZigbeeAddress());
			ps.setString(11, driver.getFirmware());
			ps.setString(12, driver.getSerialNumber());
			ps.setString(13, driver.getProductType());
			ps.setTimestamp(14, driver.getDeploymentDate());
			ps.setString(15, driver.getNetState());
			ps.setString(16, driver.getDataAcceptanceState());
			ps.setString(17, driver.getParametrizationState());
			ps.setString(18, driver.getDataSearchingState());
			ps.setInt(19, driver.getId());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(ps, conn);
		}
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
