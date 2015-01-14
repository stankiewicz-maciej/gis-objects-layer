package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.CabinetDao;
import pl.edu.agh.ztb.mod2.model.Cabinet;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;

public class CabinetDaoImpl implements CabinetDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	
	@Override
	public int deleteCabinet(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Cabinet> getAllCabinets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCabinet(Cabinet cabinet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCabinet(Cabinet cabinet) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cabinet getCabinet(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
