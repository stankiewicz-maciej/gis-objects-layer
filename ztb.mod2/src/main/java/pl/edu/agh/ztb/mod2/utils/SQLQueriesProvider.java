package pl.edu.agh.ztb.mod2.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import pl.edu.agh.ztb.mod2.Main;

public class SQLQueriesProvider {

	private static SQLQueriesProvider instance = null;
	private static final String SQL_QUERIES_FILE = "sqlQueries.properties";
	private Properties queries = null;

	private SQLQueriesProvider() {
		queries = new Properties();
		FileInputStream in = null;
		try {
			ClassLoader classLoader = Main.class.getClassLoader();
			in = new FileInputStream(classLoader.getResource(SQL_QUERIES_FILE).getFile());
			queries.load(in);
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

	public static SQLQueriesProvider getInstance() {
		if (instance == null) {
			synchronized (SQLQueriesProvider.class) {
				if (instance == null)
					instance = new SQLQueriesProvider();
			}
		}
		return instance;
	}

	public Properties getQueries() {
		return queries;
	}

	
}
