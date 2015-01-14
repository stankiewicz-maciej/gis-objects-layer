package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.model.Driver;

public interface DriverDao {

	Set<Driver> getAllDrivers();
	Driver getDriver(int id);
	Driver getDriverByFixture(int fixtureId);
	int deleteDriver(int id);
	int insertDriver(Driver driver);
	int updateDriver(Driver driver);
}
