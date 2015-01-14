package pl.edu.agh.ztb.mod2.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import pl.edu.agh.ztb.mod2.Main;

public class ConnectionFactory {

	private static ConnectionFactory instance = null;
	private static final Driver JDBC_DRIVER = new org.postgresql.Driver();

	private Connection connection = null;

	private String url;
	private String user;
	private String pass;

	private ConnectionFactory() {
		try {
			DriverManager.registerDriver(JDBC_DRIVER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		url = Main.getProperties().getProperty("db.url");
		user = Main.getProperties().getProperty("db.user");
		pass = Main.getProperties().getProperty("db.pass");
	}

	public static ConnectionFactory getInstance() {
		if (instance == null)
			instance = new ConnectionFactory();
		return instance;
	}

	public Connection getConnection() {
		try {
			if ((connection == null) || connection.isClosed())
				connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
