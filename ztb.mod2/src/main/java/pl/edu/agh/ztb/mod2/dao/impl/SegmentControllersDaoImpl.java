package pl.edu.agh.ztb.mod2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.SegmentControllerDao;
import pl.edu.agh.ztb.mod2.model.SegmentController;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class SegmentControllersDaoImpl implements SegmentControllerDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();
	
	@Override
	public int deleteSegmentController(int id) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("segment_ctrl.delete"));
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
	public Set<SegmentController> getAllSegmentControllers() {
		Set<SegmentController> set = new HashSet<SegmentController>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("segment_ctrl.get.all"));
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new SegmentController(rs.getInt("id"), rs.getInt("cabinet_id"), rs.getString("firmware"), rs.getString("product_code"), rs.getString("number")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public SegmentController getSegmentController(int id) {
		SegmentController sc = null;
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("segment_ctrl.get.id"));
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next())
				sc = new SegmentController(rs.getInt("id"), rs.getInt("cabinet_id"), rs.getString("firmware"), rs.getString("product_code"), rs.getString("number"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return sc;
	}

	@Override
	public Set<SegmentController> getSegmentControllerByCabinet(int cabinetId) {
		Set<SegmentController> set = new HashSet<SegmentController>();
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("segment_ctrl.get.cabinet_id"));
			ps.setInt(1, cabinetId);
			rs = ps.executeQuery();
			while (rs.next()) {
				set.add(new SegmentController(rs.getInt("id"), rs.getInt("cabinet_id"), rs.getString("firmware"), rs.getString("product_code"), rs.getString("number")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return set;
	}

	@Override
	public int insertSegmentController(SegmentController segmentController) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("segment_ctrl.insert"));
			ps.setInt(1, segmentController.getCabinet_id());
			ps.setString(2, segmentController.getFirmware());
			ps.setString(3, segmentController.getProduct_code());
			ps.setString(4, segmentController.getNumber());
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
	public int updateSegmentController(SegmentController segmentController) {
		Connection conn = cm.getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(queries.getProperty("segment_ctrl.update"));
			ps.setInt(1, segmentController.getCabinet_id());
			ps.setString(2, segmentController.getFirmware());
			ps.setString(3, segmentController.getProduct_code());
			ps.setString(4, segmentController.getNumber());
			ps.setInt(5, segmentController.getId());
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
