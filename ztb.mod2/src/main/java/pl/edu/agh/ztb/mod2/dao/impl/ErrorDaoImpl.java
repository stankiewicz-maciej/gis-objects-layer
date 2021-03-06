package pl.edu.agh.ztb.mod2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.dao.ErrorDao;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;
import pl.edu.agh.ztb.mod2.model.Error;


public class ErrorDaoImpl implements ErrorDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();

	@Override
	public Set<Error> getAllErrors() throws DataObjectDaoException {
		Set<Error> set = new HashSet<Error>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.get.all"));
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new Error(rs.getInt("id"), rs.getInt("fixture_id"), rs.getInt("driver_id"), rs.getTimestamp("timestamp"), rs.getString("error_type")));
			}
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during getting all errors", e);
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public Set<Error> getFixtureErrors(int fixtureId) throws DataObjectDaoException {
		Set<Error> set = new HashSet<Error>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.get.fixture_id"));
			ps.setInt(1, fixtureId);
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new Error(rs.getInt("id"), rs.getInt("fixture_id"), rs.getInt("driver_id"), rs.getTimestamp("timestamp"), rs.getString("error_type")));
			}
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during  getting fixture error", e);
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public Set<Error> getDriverErrors(int driverId) throws DataObjectDaoException {
			Set<Error> set = new HashSet<Error>();
			Connection conn = cm.getConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(queries.getProperty("errors.get.driver_id"));
				ps.setInt(1, driverId);
				rs = ps.executeQuery();
				while (rs.next()) {
					set.add(new Error(rs.getInt("id"), rs.getInt("fixture_id"), rs.getInt("driver_id"), rs.getTimestamp("timestamp"), rs.getString("error_type")));
				}
			} catch (SQLException e) {
				throw new DataObjectDaoException("Error during  getting driver error", e);
			} finally {
				close(rs, ps, conn);
			}
			return set;
	}

	@Override
	public int clearAllErrors() throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.delete.all"));
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during clearing all errors", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int clearFixtureErrors(int fixtureId) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.delete.fixture"));
			ps.setInt(1, fixtureId);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during clearing fixture error", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int clearDriverErrors(int driverId) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.delete.driver"));
			ps.setInt(1, driverId);
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during clearing driver error", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int insertFixtureError(Error error) throws DataObjectDaoException {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.insert.fixture"));
			ps.setInt(1, error.getFixture_id());
			ps.setTimestamp(2, error.getTimestamp());
			ps.setString(3, error.getError_type());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during inserting fixture error", e);
		} finally {
			close(ps, conn);
		}
	}

	@Override
	public int insertDriverError(Error error) throws DataObjectDaoException {
	Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("errors.insert.driver"));
			ps.setInt(1, error.getDriver_id());
			ps.setTimestamp(2, error.getTimestamp());
			ps.setString(3, error.getError_type());
			int rs = ps.executeUpdate();
			return rs;
		} catch (SQLException e) {
			throw new DataObjectDaoException("Error during inserting driver error", e);
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
