package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.model.Error;

public interface ErrorDao {

	Set<Error> getAllErrors() throws DataObjectDaoException;
	Set<Error> getFixtureErrors(int fixtureId) throws DataObjectDaoException;
	Set<Error> getDriverErrors(int driverId) throws DataObjectDaoException;
	int clearAllErrors() throws DataObjectDaoException;
	int clearFixtureErrors(int fixtureId) throws DataObjectDaoException;
	int clearDriverErrors(int driverId) throws DataObjectDaoException;
	int insertFixtureError(Error error) throws DataObjectDaoException;
	int insertDriverError(Error error) throws DataObjectDaoException;
}
