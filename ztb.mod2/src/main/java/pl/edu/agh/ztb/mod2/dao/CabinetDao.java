package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.model.Cabinet;

public interface CabinetDao {

	Set<Cabinet> getAllCabinets();
	Cabinet getCabinet(int id);
	int deleteCabinet(int id);
	int insertCabinet(Cabinet cabinet);
	int updateCabinet(Cabinet cabinet);
}
