package pl.edu.agh.ztb.mod2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlproc.engine.SqlProcessorException;
import org.sqlproc.engine.SqlSession;
import org.sqlproc.engine.SqlSessionFactory;
import org.sqlproc.engine.jdbc.JdbcSessionFactory;
import org.sqlproc.engine.util.DDLLoader;

import pl.edu.agh.ztb.mod2.dao.*;
import pl.edu.agh.ztb.mod2.dao.impl.*;
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
        	in = new FileInputStream(classLoader.getResource(DB_PROPERTIES_FILE).getFile());
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			//TODO: log
		} finally {
			try {
                 if (in != null) {
                     in.close();
                 }
            } catch (IOException e) {
               //TODO: log
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
        sqlSession.executeBatch((DB_CLEAR != null) ? DB_CLEAR : ddls.toArray(new String[0]));
        } catch (SqlProcessorException e) {
        	//TODO: for init debug only
        	SQLException ex = ((SQLException) e.getCause()).getNextException();
        	ex.printStackTrace();
        	int[] updateCount = ((BatchUpdateException) e.getCause()).getUpdateCounts();
            int count = 1;
            for (int i : updateCount) {
                if  (i == Statement.EXECUTE_FAILED) {
                    System.out.println("Error on request " + count +": Execute failed");
                } else {
                	System.out.println("Request " + count +": OK");
                }
                count++;    
            }
        }
    }

    

   
    public static void main(String[] args) throws Exception {
       
        Main main = new Main();
        main.setupDb();
        main.getFixturesDao();
      
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

	public void setSegmentControllerDao(SegmentControllerDao segmentControllerDao) {
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
