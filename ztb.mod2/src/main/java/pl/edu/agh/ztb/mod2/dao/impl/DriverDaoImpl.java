package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.DriverDao;
import pl.edu.agh.ztb.mod2.model.Driver;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class DriverDaoImpl implements DriverDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();
	
	@Override
	public Set<Driver> getAllDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Driver getDriver(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Driver getDriverByFixture(int fixtureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteDriver(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDriver(Driver driver) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDriver(Driver driver) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
