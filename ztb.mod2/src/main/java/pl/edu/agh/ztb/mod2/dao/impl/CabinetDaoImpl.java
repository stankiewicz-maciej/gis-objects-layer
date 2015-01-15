package pl.edu.agh.ztb.mod2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.CabinetDao;
import pl.edu.agh.ztb.mod2.model.Cabinet;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class CabinetDaoImpl implements CabinetDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();

	@Override
	public int deleteCabinet(int id) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("cabinet.delete"));
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
	public Set<Cabinet> getAllCabinets() {
		Set<Cabinet> set = new HashSet<Cabinet>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("cabinet.get.all"));
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new Cabinet(rs.getInt("id"), rs.getInt("location_id"), rs.getString("number")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public int insertCabinet(Cabinet cabinet) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("cabinet.insert"));
			ps.setInt(1, cabinet.getLocation_id());
			ps.setString(2, cabinet.getNumber());
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
	public int updateCabinet(Cabinet cabinet) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("cabinet.update"));
			ps.setInt(1, cabinet.getLocation_id());
			ps.setString(2, cabinet.getNumber());
			ps.setInt(3, cabinet.getId());
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
	public Cabinet getCabinet(int id) {
		Cabinet c = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("cabinet.get.id"));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				c = new Cabinet(rs.getInt("id"), rs.getInt("location_id"), rs.getString("number"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return c;
	}

	private void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(PreparedStatement ps, Connection conn) {
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
