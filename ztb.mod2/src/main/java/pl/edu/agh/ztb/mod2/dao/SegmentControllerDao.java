package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.DataObjectDaoException;
import pl.edu.agh.ztb.mod2.model.SegmentController;

public interface SegmentControllerDao {

	Set<SegmentController> getAllSegmentControllers() throws DataObjectDaoException;
	SegmentController getSegmentController(int id) throws DataObjectDaoException;
	Set<SegmentController> getSegmentControllerByCabinet(int cabinetId) throws DataObjectDaoException;
	int deleteSegmentController(int id) throws DataObjectDaoException;
	int insertSegmentController(SegmentController segmentController) throws DataObjectDaoException;
	int updateSegmentController(SegmentController segmentController) throws DataObjectDaoException;
}
