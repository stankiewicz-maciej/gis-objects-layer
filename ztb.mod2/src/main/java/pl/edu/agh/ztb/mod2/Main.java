package pl.edu.agh.ztb.mod2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlproc.engine.SqlProcessorException;
import org.sqlproc.engine.SqlSession;
import org.sqlproc.engine.SqlSessionFactory;
import org.sqlproc.engine.jdbc.JdbcSessionFactory;
import org.sqlproc.engine.util.DDLLoader;

import pl.edu.agh.ztb.mod2.dao.CabinetDao;
import pl.edu.agh.ztb.mod2.dao.DriverDao;
import pl.edu.agh.ztb.mod2.dao.ErrorDao;
import pl.edu.agh.ztb.mod2.dao.FixturesDao;
import pl.edu.agh.ztb.mod2.dao.SegmentControllerDao;
import pl.edu.agh.ztb.mod2.dao.SensorDao;
import pl.edu.agh.ztb.mod2.dao.impl.CabinetDaoImpl;
import pl.edu.agh.ztb.mod2.dao.impl.DriverDaoImpl;
import pl.edu.agh.ztb.mod2.dao.impl.ErrorDaoImpl;
import pl.edu.agh.ztb.mod2.dao.impl.FixturesDaoImpl;
import pl.edu.agh.ztb.mod2.dao.impl.SegmentControllersDaoImpl;
import pl.edu.agh.ztb.mod2.dao.impl.SensorDaoImpl;
import pl.edu.agh.ztb.mod2.model.Cabinet;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;

public class Main {

	private static final String DB_PROPERTIES_FILE = "db.properties";
	private static final String[] DB_CLEAR = null;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private Connection connection;
	private SqlSessionFactory sessionFactory;
	private List<String> ddls;

	private FixturesDao fixturesDao;
	private CabinetDao cabinetDao;
	private DriverDao driverDao;
	private ErrorDao errorDao;
	private SegmentControllerDao segmentControllerDao;
	private SensorDao sensorDao;

	private static Properties props = null;

	static {
		initProperties();
	}

	public Main() throws SQLException {
		String initTablesDDL = props.getProperty("db.initTables");
		connection = ConnectionFactory.getInstance().getConnection();
		sessionFactory = new JdbcSessionFactory(connection);
		ddls = DDLLoader.getDDLs(this.getClass(), initTablesDDL);
		initDao();
	}

	private void initDao() {
		fixturesDao = new FixturesDaoImpl();
		cabinetDao = new CabinetDaoImpl();
		errorDao = new ErrorDaoImpl();
		driverDao = new DriverDaoImpl();
		segmentControllerDao = new SegmentControllersDaoImpl();
		sensorDao = new SensorDaoImpl();
	}

	private static void initProperties() {
		props = new Properties();
		FileInputStream in = null;
		try {
			ClassLoader classLoader = Main.class.getClassLoader();
			in = new FileInputStream(classLoader
					.getResource(DB_PROPERTIES_FILE).getFile());
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: log
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO: log
				e.printStackTrace();
			}
		}
	}

	public static Properties getProperties() {
		return props;
	}

	public void setupDb() throws SQLException {
		SqlSession sqlSession = sessionFactory.getSqlSession();
		try {
			sqlSession.executeBatch((DB_CLEAR != null) ? DB_CLEAR : ddls
					.toArray(new String[0]));
		} catch (SqlProcessorException e) {
			// TODO: for init debug only
			SQLException ex = ((SQLException) e.getCause()).getNextException();
			ex.printStackTrace();
			int[] updateCount = ((BatchUpdateException) e.getCause())
					.getUpdateCounts();
			int count = 1;
			for (int i : updateCount) {
				if (i == Statement.EXECUTE_FAILED) {
					System.out.println("Error on request " + count
							+ ": Execute failed");
				} else {
					System.out.println("Request " + count + ": OK");
				}
				count++;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//to test run as java app
		Main main = new Main();
		main.setupDb();
		CabinetDao cd = main.getCabinetDao();
		Cabinet c = new Cabinet(1, "ABC");
		cd.insertCabinet(c);
		Set<Cabinet> set = cd.getAllCabinets();
		for (Cabinet cabinet : set) {
			System.out.println(cabinet);
		}
		Cabinet c1 = cd.getCabinet(1);
		System.out.println(c1);
		System.out.println(cd.deleteCabinet(1));
		Set<Cabinet> set1 = cd.getAllCabinets();
		for (Cabinet cabinet : set1) {
			System.out.println(cabinet);
		}
	}

	public FixturesDao getFixturesDao() {
		return fixturesDao;
	}

	public CabinetDao getCabinetDao() {
		return cabinetDao;
	}

	public void setCabinetDao(CabinetDao cabinetDao) {
		this.cabinetDao = cabinetDao;
	}

	public DriverDao getDriverDao() {
		return driverDao;
	}

	public void setDriverDao(DriverDao driverDao) {
		this.driverDao = driverDao;
	}

	public ErrorDao getErrorDao() {
		return errorDao;
	}

	public void setErrorDao(ErrorDao errorDao) {
		this.errorDao = errorDao;
	}

	public SegmentControllerDao getSegmentControllerDao() {
		return segmentControllerDao;
	}

	public void setSegmentControllerDao(
			SegmentControllerDao segmentControllerDao) {
		this.segmentControllerDao = segmentControllerDao;
	}

	public SensorDao getSensorDao() {
		return sensorDao;
	}

	public void setSensorDao(SensorDao sensorDao) {
		this.sensorDao = sensorDao;
	}

	public void setFixturesDao(FixturesDao fixturesDao) {
		this.fixturesDao = fixturesDao;
	}

}
