package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.model.Fixture;

public interface FixturesDao {

	Set<Fixture> getAllFixtures() throws DataObjectDaoException;
	Fixture getFixture(int id) throws DataObjectDaoException;
	Fixture getFixtureByLocation(int locationId) throws DataObjectDaoException;
	Set<Fixture> getFixturesBySegmentCtrl(int segmentCtrlId) throws DataObjectDaoException;
	int deleteFixture(int id) throws DataObjectDaoException;
	int insertFixture(Fixture fixture) throws DataObjectDaoException;
	int updateFixture(Fixture fixture) throws DataObjectDaoException;
}
