package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.model.Cabinet;

public interface CabinetDao {

	Set<Cabinet> getAllCabinets() throws DataObjectDaoException;
	Cabinet getCabinet(int id) throws DataObjectDaoException;
	Cabinet getCabinetByLocation(int locationId) throws DataObjectDaoException;
	int deleteCabinet(int id) throws DataObjectDaoException;
	int insertCabinet(Cabinet cabinet) throws DataObjectDaoException;
	int updateCabinet(Cabinet cabinet) throws DataObjectDaoException;
}
