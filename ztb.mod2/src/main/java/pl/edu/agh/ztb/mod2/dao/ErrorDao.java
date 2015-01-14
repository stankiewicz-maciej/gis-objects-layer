package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

public interface ErrorDao {

	Set<Error> getAllErrors();
	Set<Error> getFixtureErrors(int fixtureId);
	Set<Error> getDriverErrors(int driverId);
	int clearAllErrors();
	int clearFixtureErrors(int fixtureId);
	int clearDriverErrors(int driverId);
	int insertFixtureError(int fixtureId, Error error);
	int insertDriverError(int driverId, Error error);
}
