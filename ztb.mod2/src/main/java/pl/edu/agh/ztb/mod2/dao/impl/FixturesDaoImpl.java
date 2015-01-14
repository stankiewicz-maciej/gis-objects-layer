package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.FixturesDao;
import pl.edu.agh.ztb.mod2.model.Fixture;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;

public class FixturesDaoImpl implements FixturesDao {
	
	private ConnectionFactory cm = ConnectionFactory.getInstance();

	@Override
	public Set<Fixture> getAllFixtures() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fixture getFixture(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Fixture> getFixturesBySegmentCtrl(int segmentCtrlId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteFixture(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertFixture(Fixture fixture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateFixture(Fixture fixture) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
