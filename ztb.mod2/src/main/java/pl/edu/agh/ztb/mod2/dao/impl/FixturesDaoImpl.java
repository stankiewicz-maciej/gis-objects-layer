package pl.edu.agh.ztb.mod2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.dao.FixturesDao;
import pl.edu.agh.ztb.mod2.model.Fixture;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class FixturesDaoImpl implements FixturesDao {
	
	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();
	
	@Override
	public Set<Fixture> getAllFixtures() throws DataObjectDaoException {
		Set<Fixture> set = new HashSet<Fixture>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.get.all"));
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new Fixture(rs.getInt("id"), rs.getInt("location_id"), rs.getInt("segment_ctrl_id"), rs.getString("actual_state"), rs.getString("dim_level"), rs.getDouble("hours_on"), rs.getTimestamp("time_of_last_switch_on"), rs.getTimestamp("time_of_last_switch_off"), rs.getString("hid_status"), rs.getString("device_type"), rs.getString("ballasts_and_work_type"), rs.getString("voltage_reset"), rs.getString("min_dim_level")));
			}
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting all fixtures", e);
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public Fixture getFixture(int id) throws DataObjectDaoException {
		Fixture c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.get.id"));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				c = new Fixture(rs.getInt("id"), rs.getInt("location_id"), rs.getInt("segment_ctrl_id"), rs.getString("actual_state"), rs.getString("dim_level"), rs.getDouble("hours_on"), rs.getTimestamp("time_of_last_switch_on"), rs.getTimestamp("time_of_last_switch_off"), rs.getString("hid_status"), rs.getString("device_type"), rs.getString("ballasts_and_work_type"), rs.getString("voltage_reset"), rs.getString("min_dim_level"));
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting fixture", e);
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	@Override
	public Fixture getFixtureByLocation(int locationId) throws DataObjectDaoException {
		Fixture c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.get.location_id"));
			ps.setInt(1, locationId);
			rs = ps.executeQuery();
			if (rs.next())
				c = new Fixture(rs.getInt("id"), rs.getInt("location_id"), rs.getInt("segment_ctrl_id"), rs.getString("actual_state"), rs.getString("dim_level"), rs.getDouble("hours_on"), rs.getTimestamp("time_of_last_switch_on"), rs.getTimestamp("time_of_last_switch_off"), rs.getString("hid_status"), rs.getString("device_type"), rs.getString("ballasts_and_work_type"), rs.getString("voltage_reset"), rs.getString("min_dim_level"));
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting fixture", e);
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	@Override
	public Set<Fixture> getFixturesBySegmentCtrl(int segmentCtrlId) throws DataObjectDaoException {
		Set<Fixture> set = new HashSet<Fixture>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.get.segment_ctrl_id"));
			ps.setInt(1, segmentCtrlId);
			rs = ps.executeQuery();
			while (rs.next()) 
				set.add(new Fixture(rs.getInt("id"), rs.getInt("location_id"), rs.getInt("segment_ctrl_id"), rs.getString("actual_state"), rs.getString("dim_level"), rs.getDouble("hours_on"), rs.getTimestamp("time_of_last_switch_on"), rs.getTimestamp("time_of_last_switch_off"), rs.getString("hid_status"), rs.getString("device_type"), rs.getString("ballasts_and_work_type"), rs.getString("voltage_reset"), rs.getString("min_dim_level")));
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting fixture", e);
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public int deleteFixture(int id) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.delete"));
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during deleting fixture", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int insertFixture(Fixture fixture) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.insert"));
			ps.setInt(1, fixture.getLocation_id());
			ps.setInt(2, fixture.getSegment_ctrl_id());
			ps.setString(3, fixture.getActual_state());
			ps.setString(4, fixture.getDim_level());
			ps.setDouble(5, fixture.getHours_on());
			ps.setTimestamp(6, fixture.getTime_of_last_switch_on());
			ps.setTimestamp(7, fixture.getTime_of_last_switch_off());
			ps.setString(8, fixture.getHid_status());
			ps.setString(9, fixture.getDevice_type());
			ps.setString(10, fixture.getBallasts_and_work_type());
			ps.setString(11, fixture.getVoltage_reset());
			ps.setString(12, fixture.getMin_dim_level());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during inserting fixture", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int updateFixture(Fixture fixture) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("fixture.update"));
			ps.setInt(1, fixture.getLocation_id());
			ps.setInt(2, fixture.getSegment_ctrl_id());
			ps.setString(3, fixture.getActual_state());
			ps.setString(4, fixture.getDim_level());
			ps.setDouble(5, fixture.getHours_on());
			ps.setTimestamp(6, fixture.getTime_of_last_switch_on());
			ps.setTimestamp(7, fixture.getTime_of_last_switch_off());
			ps.setString(8, fixture.getHid_status());
			ps.setString(9, fixture.getDevice_type());
			ps.setString(10, fixture.getBallasts_and_work_type());
			ps.setString(11, fixture.getVoltage_reset());
			ps.setString(12, fixture.getMin_dim_level());
			ps.setInt(13, fixture.getId());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during updating fixture", e);
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
