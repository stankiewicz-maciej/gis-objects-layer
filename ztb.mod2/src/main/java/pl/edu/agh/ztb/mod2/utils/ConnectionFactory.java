package pl.edu.agh.ztb.mod2.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.edu.agh.ztb.mod2.ObjectLayerService;

public class ConnectionFactory {

	private static ConnectionFactory instance = null;
	private static final Driver JDBC_DRIVER = new org.postgresql.Driver();

	private String url;
	private String user;
	private String pass;

	private ConnectionFactory() {
		try {
			DriverManager.registerDriver(JDBC_DRIVER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		url = ObjectLayerService.getProperties().getProperty("db.url");
		user = ObjectLayerService.getProperties().getProperty("db.user");
		pass = ObjectLayerService.getProperties().getProperty("db.pass");
	}

	public static ConnectionFactory getInstance() {
		if (instance == null) {
			synchronized (ConnectionFactory.class) {
				if (instance == null)
					instance = new ConnectionFactory();
			}
		}
		return instance;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	

}
