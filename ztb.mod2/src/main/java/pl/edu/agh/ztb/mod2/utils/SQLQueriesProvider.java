package pl.edu.agh.ztb.mod2.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import pl.edu.agh.ztb.mod2.ObjectLayerService;

public class SQLQueriesProvider {

	private static SQLQueriesProvider instance = null;
	private static final String SQL_QUERIES_FILE = "sqlQueries.properties";
	private Properties queries = null;

	private SQLQueriesProvider() {
		queries = new Properties();
		InputStream in = null;
		try {
			ClassLoader classLoader = ObjectLayerService.class.getClassLoader();
			in = classLoader.getResourceAsStream(SQL_QUERIES_FILE);
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
