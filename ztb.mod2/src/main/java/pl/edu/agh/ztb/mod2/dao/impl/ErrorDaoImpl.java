package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.ErrorDao;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;

public class ErrorDaoImpl implements ErrorDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	
	@Override
	public Set<Error> getAllErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Error> getFixtureErrors(int fixtureId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Error> getDriverErrors(int driverId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int clearAllErrors() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int clearFixtureErrors(int fixtureId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int clearDriverErrors(int driverId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertFixtureError(int fixtureId, Error error) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDriverError(int driverId, Error error) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
