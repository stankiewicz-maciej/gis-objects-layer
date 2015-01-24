package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.model.Driver;

public interface DriverDao {

	Set<Driver> getAllDrivers() throws DataObjectDaoException;
	Driver getDriver(int id) throws DataObjectDaoException;
	Driver getDriverByFixture(int fixtureId) throws DataObjectDaoException;
	int deleteDriver(int id) throws DataObjectDaoException;
	int insertDriver(Driver driver) throws DataObjectDaoException;
	int updateDriver(Driver driver) throws DataObjectDaoException;
}
