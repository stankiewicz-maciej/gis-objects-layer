package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;
import pl.edu.agh.ztb.mod2.model.Error;

public interface ErrorDao {

	Set<Error> getAllErrors();
	Set<Error> getFixtureErrors(int fixtureId);
	Set<Error> getDriverErrors(int driverId);
	int clearAllErrors();
	int clearFixtureErrors(int fixtureId);
	int clearDriverErrors(int driverId);
	int insertFixtureError(Error error);
	int insertDriverError(Error error);
}
