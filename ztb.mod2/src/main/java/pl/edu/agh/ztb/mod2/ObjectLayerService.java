package pl.edu.agh.ztb.mod2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.Set;

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
import pl.edu.agh.ztb.mod2.model.Driver;
import pl.edu.agh.ztb.mod2.model.Error;
import pl.edu.agh.ztb.mod2.model.Fixture;
import pl.edu.agh.ztb.mod2.model.SegmentController;
import pl.edu.agh.ztb.mod2.model.Sensor;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;

public class ObjectLayerService {

	private static final String DB_PROPERTIES_FILE = "db.properties";
	private static final String[] DB_CLEAR = null;

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
		try {
			initProperties();
		} catch (DataObjectDaoException e) {
			e.printStackTrace();
		}
	}

	public ObjectLayerService() {
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

	private static void initProperties() throws DataObjectDaoException {
		props = new Properties();
		InputStream in = null;
		try {
			ClassLoader classLoader = ObjectLayerService.class.getClassLoader();
			in = classLoader.getResourceAsStream(DB_PROPERTIES_FILE);
			props.load(in);
		} catch (Exception e) {
			throw new DataObjectDaoException("Error during loading db properties file", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				throw new DataObjectDaoException("Error during closing db properties file", e);
			}
		}
	}

	public static Properties getProperties() {
		return props;
	}

	public void initDB() throws DataObjectDaoException {
		SqlSession sqlSession = sessionFactory.getSqlSession();
		try {
			sqlSession.executeBatch((DB_CLEAR != null) ? DB_CLEAR : ddls
					.toArray(new String[0]));
		} catch (SqlProcessorException e) {
			throw new DataObjectDaoException("Error during database setup", e);
		}
	}

	public static void main(String[] args) throws DataObjectDaoException {
		//to test run as java app
		ObjectLayerService main = new ObjectLayerService();
		if (args.length > 0 && args[0].equals("init"))
			main.initDB();
		
		
		CabinetDao cd = main.getCabinetDao();
		Cabinet c = new Cabinet(1, "ABC");
		cd.insertCabinet(c);
		Set<Cabinet> set = cd.getAllCabinets();
		for (Cabinet cabinet : set) {	
			System.out.println(cabinet);
		}
		Cabinet c1 = cd.getCabinet(1);
		System.out.println(c1);
		/*//System.out.println(cd.deleteCabinet(1));
		Set<Cabinet> set1 = cd.getAllCabinets();
		for (Cabinet cabinet : set1) {
			System.out.println(cabinet);
		}*/
		


		//********************************************************

		SegmentControllerDao sgd = main.getSegmentControllerDao();
		SegmentController controller = new SegmentController(1, 1, "firmware", "code", "C3PO");
		sgd.insertSegmentController(controller);
		Set<SegmentController> controllerSet = sgd.getAllSegmentControllers();
		for (SegmentController controller2 : controllerSet) {
			System.out.println(controller2);
		}

		SegmentController control = sgd.getSegmentController(1);
		System.out.println(control);
		control.setFirmware("firmware2");
		sgd.updateSegmentController(control);
		control = sgd.getSegmentController(1);
		System.out.println(control);
		controllerSet = sgd.getAllSegmentControllers();
		for (SegmentController controller2 : controllerSet) {
			System.out.println(controller2);
		}
		controllerSet = sgd.getSegmentControllerByCabinet(1);
		for (SegmentController controller2 : controllerSet) {
			System.out.println(controller2);
		}
		/*sgd.deleteSegmentController(1);
		controllerSet = sgd.getAllSegmentControllers();
		for (SegmentController controller2 : controllerSet) {
			System.out.println(controller2);
		}*/

		//**********************FIXTURES**********************************

		FixturesDao fd = main.getFixturesDao();
		Fixture fixture = new Fixture(1, 1, "on", "dim", 23.4, new Timestamp(23498742), new Timestamp(123874234), "status", "lamp", "ballast", "voltage", "mindimlevel");
		fd.insertFixture(fixture);
		Set<Fixture> fixtureSet = fd.getAllFixtures();
		for (Fixture controller2 : fixtureSet) {
			System.out.println(controller2);
		}

		Fixture fixture3 = fd.getFixture(1);
		System.out.println(fixture3);
		fixture3.setActual_state("OFF");
		fd.updateFixture(fixture3);
		Fixture fixture4 = fd.getFixture(1);
		System.out.println(fixture4);
		fixtureSet = fd.getAllFixtures();
		for (Fixture fixture2 : fixtureSet) {
			System.out.println(fixture2);
		}
		fixtureSet = fd.getFixturesBySegmentCtrl(1);
		for (Fixture fixture2 : fixtureSet) {
			System.out.println(fixture2);
		}
		/*fd.deleteFixture(1);
		fixtureSet = fd.getAllFixtures();
		for (Fixture fixture2 : fixtureSet) {
			System.out.println(fixture2);
		}
		*/
		//// SENSORS ////////
		Sensor s = new Sensor(1, 1, 1);
		SensorDao sensorDao = main.getSensorDao();
		sensorDao.insertSensor(s);
		s = sensorDao.getSensor(1);
		System.out.println(s);
		Set<Sensor> sensorSet = sensorDao.getAllSensors();
		for (Sensor x : sensorSet) {
			System.out.println(x);
		}
		s.setSensor_type_id(2);
		sensorDao.updateSensor(s);
		System.out.println(sensorDao.getSensor(1));
		Properties data = new Properties();
		data.setProperty("a", "A");
		data.setProperty("b", "B");
		data.setProperty("c", "C");
		data.list(System.out);
		sensorDao.updateSensorData(s.getId(), data);
		sensorDao.getSensorData(s.getId()).list(System.out);
		data.remove("a");
		data.setProperty("d",  "D");
		sensorDao.updateSensorData(s.getId(), data);
		sensorDao.getSensorData(s.getId()).list(System.out);
		
		
		/// DRIVERS ////
		
		DriverDao dd = main.getDriverDao();
		Driver driver = new Driver(1, "temp", "on", new Timestamp(23498742), "powerUsage", "21V", "20A", "power", "cosValue", "Zigbee", "firmware", "as8dyasdh", "productType",new Timestamp(928379823), "netState", "dataAcceptance", "parametrizationState", "syncState");
		dd.insertDriver(driver);
		Set<Driver> driverSet = dd.getAllDrivers();
		for (Driver controller2 : driverSet) {
			System.out.println(controller2);
		}
		Driver d = dd.getDriver(1);
		System.out.println(d);
		d.setConnectionQuality("off");
		dd.updateDriver(d);
		Driver edr = dd.getDriver(1);
		System.out.println(edr);
		
		Driver f = dd.getDriverByFixture(1);
		System.out.println(f);
		
		///ERRORS?////
		ErrorDao ed = main.getErrorDao();
		Error e = new Error(1, 0, new Timestamp(50000),"ABC");
		ed.insertFixtureError(e);
		Set<Error> errorSet = ed.getAllErrors();
		for (Cabinet cabinet : set) {
			System.out.println(cabinet);
		}
		errorSet = ed.getFixtureErrors(1);
		for (Error error : errorSet) {
			System.out.println(error);
		}

		ed.getFixtureErrors(1);
		errorSet = ed.getFixtureErrors(1);
		for (Error error : errorSet) {
			System.out.println(error);
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
