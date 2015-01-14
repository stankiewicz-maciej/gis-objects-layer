package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.model.Fixture;

public interface FixturesDao {

	Set<Fixture> getAllFixtures();
	Fixture getFixture(int id);
	Set<Fixture> getFixturesBySegmentCtrl(int segmentCtrlId);
	int deleteFixture(int id);
	int insertFixture(Fixture fixture);
	int updateFixture(Fixture fixture);
}
