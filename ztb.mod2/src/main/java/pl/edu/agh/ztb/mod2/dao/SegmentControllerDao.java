package pl.edu.agh.ztb.mod2.dao;

import java.util.Set;

import pl.edu.agh.ztb.mod2.model.SegmentController;

public interface SegmentControllerDao {

	Set<SegmentController> getAllSegmentControllers();
	SegmentController getSegmentController(int id);
	int deleteSegmentController(int id);
	int insertSegmentController(SegmentController segmentController);
	int updateSegmentController(SegmentController segmentController);
}
