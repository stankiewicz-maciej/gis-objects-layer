package pl.edu.agh.ztb.mod2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

public class Main {

	private static final String DB_PROPERTIES_FILE = "db.properties";
	private static final String[] DB_CLEAR = null;

	//private final Logger logger = LoggerFactory.getLogger(getClass());

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

	public Main() {
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
		FileInputStream in = null;
		try {
			ClassLoader classLoader = Main.class.getClassLoader();
			in = new FileInputStream(classLoader
					.getResource(DB_PROPERTIES_FILE).getFile());
			props.load(in);
		} catch (IOException e) {
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

	public void setupDb() throws DataObjectDaoException {
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
			throw new DataObjectDaoException("error during setup database", e);
		}
	}

	public void initDB() throws DataObjectDaoException {
		Main main = new Main();
		main.setupDb();
		CabinetDao cd = main.getCabinetDao();
		SegmentControllerDao sgd = main.getSegmentControllerDao();
		FixturesDao fd = main.getFixturesDao();
		DriverDao dd = main.getDriverDao();
		ErrorDao ed = main.getErrorDao();
		SensorDao sensorDao = main.getSensorDao();

		Cabinet c = new Cabinet(1, "PX1234");
		cd.insertCabinet(c);
		c = new Cabinet(2, "AB734");
		cd.insertCabinet(c);

		SegmentController controller = new SegmentController(1, "TC-017A", "SC-1011-1230-0000-0000", "PL_KRK_0010001-991");
		sgd.insertSegmentController(controller);
		SegmentController controller2 = new SegmentController(1, "AS-122", "SC-1011-1230-0000-0000", "PL_KRK_0010002-931");
		sgd.insertSegmentController(controller2);
		SegmentController controller3 = new SegmentController(2, "XY-22", "SC-1011-1230-7654-0000", "PL_KRK_0012302-931");
		sgd.insertSegmentController(controller2);

		Fixture fixture = new Fixture(1, 1, "on", "10%", 23.4, new Timestamp(1422132114), new Timestamp(1422136114), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "7%");
		fd.insertFixture(fixture);
		fixture = new Fixture(2, 1, "on", "20%", 16.4, new Timestamp(1422136274), new Timestamp(1422136874), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "7%");
		fd.insertFixture(fixture);
		fixture = new Fixture(3, 2, "off", "0%", 16.4, new Timestamp(1422136873), new Timestamp(1422136874), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "7%");
		fd.insertFixture(fixture);
		fixture = new Fixture(4, 2, "on", "30%", 16.4, new Timestamp(1422136811), new Timestamp(1422136832), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "Yes", "0%");
		fd.insertFixture(fixture);
		fixture = new Fixture(5, 3, "on", "0%", 16.4, new Timestamp(1422133874), new Timestamp(1422136874), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "0%");
		fd.insertFixture(fixture);

		Driver driver = new Driver(1, "60", "on", new Timestamp(1422136874), "242.35KWh", "21V", "20A", "112W", "0,96", "00:13:a2:fe:8b:34:c5:11", "2.61.12", "008765676542", "LT-BGHT43",new Timestamp(1264370311), "OK", "OK", "OK", "Zsynchronizowane");
		dd.insertDriver(driver);
		driver = new Driver(2, "62", "on", new Timestamp(1422136874), "242.35KWh", "21V", "25A", "123W", "0,96", "00:13:a2:fe:8b:34:c5:12", "2.61.12", "008765676542", "LT-BGHT43",new Timestamp(1264370474), "OK", "OK", "OK", "Zsynchronizowane");
		dd.insertDriver(driver);
		driver = new Driver(3, "58", "on", new Timestamp(1422136874), "242.35KWh", "21V", "20A", "112W", "0,96", "00:13:a2:fe:8b:34:c5:13", "2.61.12", "008765676542", "LT-BGHT43",new Timestamp(1264370324), "OK", "OK", "OK", "Zsynchronizowane");
		dd.insertDriver(driver);
		driver = new Driver(4, "60", "on", new Timestamp(1422136874), "242.35KWh", "21V", "20A", "112W", "0,96", "00:13:a2:fe:8b:34:c5:14", "2.61.12", "008765676542", "LT-BGHT43",new Timestamp(1264346537), "OK", "OK", "OK", "Zsynchronizowane");
		dd.insertDriver(driver);
		driver = new Driver(5, "61", "on", new Timestamp(1422136874), "242.35KWh", "21V", "20A", "112W", "0,96", "00:13:a2:fe:8b:34:c5:15", "2.61.12", "008765676542", "LT-BGHT43",new Timestamp(1264374655), "OK", "OK", "OK", "Zsynchronizowane");
		dd.insertDriver(driver);

		Sensor s = new Sensor(1, 1, 1);
		sensorDao.insertSensor(s);
		s = sensorDao.getSensor(1);
		s.setSensor_type_id(2);
		sensorDao.updateSensor(s);
		Properties data = new Properties();
		data.setProperty("a", "A");
		data.setProperty("b", "B");
		data.setProperty("c", "C");
		data.list(System.out);
		sensorDao.updateSensorData(s.getId(), data);
		data.remove("a");
		data.setProperty("d",  "D");
		sensorDao.updateSensorData(s.getId(), data);

		Error e = new Error(3, 0, new Timestamp(543874234),"Power supply error");
		ed.insertFixtureError(e);
		e = new Error(3, 0, new Timestamp(543874234),"Software error");
		ed.insertFixtureError(e);

	}

	private void testCabinets(CabinetDao cd)  throws DataObjectDaoException {
		Cabinet c = new Cabinet(1, "PX1234");
		cd.insertCabinet(c);
		Set<Cabinet> set = cd.getAllCabinets();
		for (Cabinet cabinet : set) {
			System.out.println(cabinet);
		}
		Cabinet c1 = cd.getCabinet(1);
		System.out.println(c1);

		c = new Cabinet(2, "AB734");
		cd.insertCabinet(c);
		set = cd.getAllCabinets();
		for (Cabinet cabinet : set) {
			System.out.println(cabinet);
		}
		Cabinet c2 = cd.getCabinet(1);
		System.out.println(c2);

		/*System.out.println(cd.deleteCabinet(1));
		Set<Cabinet> set1 = cd.getAllCabinets();
		for (Cabinet cabinet : set1) {
			System.out.println(cabinet);
		}*/
	}

	private void testSegmentControllers(SegmentControllerDao sgd)  throws DataObjectDaoException {
		SegmentController controller = new SegmentController(1, "TC-017A", "SC-1011-1230-0000-0000", "PL_KRK_0010001-991");
		sgd.insertSegmentController(controller);
		Set<SegmentController> controllersSet = sgd.getAllSegmentControllers();
		for (SegmentController controllerInLoop : controllersSet) {
			System.out.println(controllerInLoop);
		}

		SegmentController control = sgd.getSegmentController(1);
		System.out.println(control);
		control.setFirmware("TC-018A");
		sgd.updateSegmentController(control);
		control = sgd.getSegmentController(1);
		System.out.println(control);
		controllersSet = sgd.getAllSegmentControllers();
		for (SegmentController controllerInLoop : controllersSet) {
			System.out.println(controllerInLoop);
		}

		SegmentController controller2 = new SegmentController(1, "AS-122", "SC-1011-1230-0000-0000", "PL_KRK_0010002-931");
		sgd.insertSegmentController(controller2);

		controllersSet = sgd.getSegmentControllerByCabinet(1);
		for (SegmentController controllerInLoop : controllersSet) {
			System.out.println(controllerInLoop);
		}

		SegmentController controller3 = new SegmentController(2, "XY-22", "SC-1011-1230-7654-0000", "PL_KRK_0012302-931");
		sgd.insertSegmentController(controller2);

		controllersSet = sgd.getSegmentControllerByCabinet(3);
		for (SegmentController controllerInLoop : controllersSet) {
			System.out.println(controllerInLoop);
		}

		/*sgd.deleteSegmentController(1);
		controllersSet = sgd.getAllSegmentControllers();
		for (SegmentController controllerInLoop : controllersSet) {
			System.out.println(controllerInLoop);
		}*/

	}

	private void testFixtures(FixturesDao fd)  throws DataObjectDaoException {

		Fixture fixture = new Fixture(1, 1, "on", "10%", 23.4, new Timestamp(23498742), new Timestamp(243874234), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "7%");
		fd.insertFixture(fixture);
		Set<Fixture> fixtureSet = fd.getAllFixtures();
		for (Fixture fixtureInLoop : fixtureSet) {
			System.out.println(fixtureInLoop);
		}

		Fixture fixture3 = fd.getFixture(1);
		System.out.println(fixture3);
		fixture3.setActual_state("OFF");
		fd.updateFixture(fixture3);
		Fixture fixture4 = fd.getFixture(1);
		System.out.println(fixture4);
		fixtureSet = fd.getAllFixtures();
		for (Fixture fixtureInLoop : fixtureSet) {
			System.out.println(fixtureInLoop);
		}
		fixtureSet = fd.getFixturesBySegmentCtrl(1);
		for (Fixture fixtureInLoop : fixtureSet) {
			System.out.println(fixtureInLoop);
		}
		/*fd.deleteFixture(1);
		fixtureSet = fd.getAllFixtures();
		for (Fixture fixtureInLoop : fixtureSet) {
			System.out.println(fixtureInLoop);
		}
		*/

		fixture = new Fixture(2, 1, "on", "20%", 16.4, new Timestamp(93498742), new Timestamp(943874234), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "7%");
		fd.insertFixture(fixture);

		fixture = new Fixture(3, 2, "off", "0%", 16.4, new Timestamp(53498742), new Timestamp(543874234), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "7%");
		fd.insertFixture(fixture);

		fixture = new Fixture(4, 2, "on", "30%", 16.4, new Timestamp(13498742), new Timestamp(143874234), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "Yes", "0%");
		fd.insertFixture(fixture);

		fixture = new Fixture(5, 3, "on", "0%", 16.4, new Timestamp(23498742), new Timestamp(243874234), "OK", "LED", "Zintegrowany zasilacz LED, Możliwy tryb AC", "No", "0%");
		fd.insertFixture(fixture);
	}

	private void testDrivers(DriverDao dd)  throws DataObjectDaoException {
		Driver driver = new Driver(1, "60", "on", new Timestamp(23498742), "242.35KWh", "21V", "20A", "112W", "0,96", "00:13:a2:fe:8b:34:c5:11", "2.61.12", "008765676542", "LT-BGHT43",new Timestamp(1028379823), "OK", "OK", "OK", "Zsynchronizowane");
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
	}

	private void testSensors(SensorDao sensorDao) throws DataObjectDaoException {
		Sensor s = new Sensor(1, 1, 1);

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

	}

	private void testErrors(ErrorDao ed)  throws DataObjectDaoException {
		Error e = new Error(3, 0, new Timestamp(543874234),"Power supply error");
		ed.insertFixtureError(e);
		Set<Error> errorSet = ed.getAllErrors();

		errorSet = ed.getFixtureErrors(1);
		for (Error error : errorSet) {
			System.out.println(error);
		}

		ed.getFixtureErrors(1);
		errorSet = ed.getFixtureErrors(1);
		for (Error error : errorSet) {
			System.out.println(error);
		}
		e = new Error(3, 0, new Timestamp(543874234),"Software error");
		ed.insertFixtureError(e);
	}

	public static void main(String[] args) throws DataObjectDaoException {
		//to test run as java app
		Main main = new Main();
		main.setupDb();

		CabinetDao cd = main.getCabinetDao();
		main.testCabinets(cd);

		SegmentControllerDao sgd = main.getSegmentControllerDao();
		main.testSegmentControllers(sgd);

		FixturesDao fd = main.getFixturesDao();
		main.testFixtures(fd);

		DriverDao dd = main.getDriverDao();
		main.testDrivers(dd);

		ErrorDao ed = main.getErrorDao();
		main.testErrors(ed);

		SensorDao sensorDao = main.getSensorDao();
		main.testSensors(sensorDao);


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
