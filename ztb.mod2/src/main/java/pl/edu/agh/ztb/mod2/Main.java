package pl.edu.agh.ztb.mod2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlproc.engine.SqlEngineFactory;
import org.sqlproc.engine.SqlFeature;
import org.sqlproc.engine.SqlProcessorException;
import org.sqlproc.engine.SqlSession;
import org.sqlproc.engine.SqlSessionFactory;
import org.sqlproc.engine.jdbc.JdbcEngineFactory;
import org.sqlproc.engine.jdbc.JdbcSessionFactory;
import org.sqlproc.engine.util.DDLLoader;

import pl.edu.agh.ztb.mod2.dao.FixturesDao;
import pl.edu.agh.ztb.mod2.dao.impl.FixturesDaoPgsql;


public class Main {

    private static final Driver JDBC_DRIVER = new org.postgresql.Driver();
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static final String DB_TYPE = SqlFeature.POSTGRESQL;
    private static final String[] DB_CLEAR = null;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Connection connection;
    private SqlSessionFactory sessionFactory;
    private SqlEngineFactory sqlFactory;
    private List<String> ddls;

    static {
        try {
            DriverManager.registerDriver(JDBC_DRIVER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Main() throws SQLException {
        JdbcEngineFactory factory = new JdbcEngineFactory();
        factory.setFilter(DB_TYPE);
        this.sqlFactory = factory;

        Properties props = new Properties();
        FileInputStream in = null;
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
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
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.pass");
        String initTablesDDL = props.getProperty("db.initTables");
        connection = DriverManager.getConnection(url, user, pass);
        sessionFactory = new JdbcSessionFactory(connection);
        ddls = DDLLoader.getDDLs(this.getClass(), initTablesDDL);
        fixturesDao = new FixturesDaoPgsql(sqlFactory, sessionFactory);
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

    private FixturesDao fixturesDao;

   
    public static void main(String[] args) throws Exception {
       
        Main main = new Main();
        main.setupDb();
        main.getFixturesDao().test();
      
    }
   
    public FixturesDao getFixturesDao() {
        return fixturesDao;
    }

}
