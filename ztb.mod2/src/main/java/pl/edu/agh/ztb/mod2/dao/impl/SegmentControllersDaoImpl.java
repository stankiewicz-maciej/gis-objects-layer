package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.Properties;
import java.util.Set;

import pl.edu.agh.ztb.mod2.dao.SegmentControllerDao;
import pl.edu.agh.ztb.mod2.model.SegmentController;
import pl.edu.agh.ztb.mod2.utils.ConnectionFactory;
import pl.edu.agh.ztb.mod2.utils.SQLQueriesProvider;

public class SegmentControllersDaoImpl implements SegmentControllerDao {

	private ConnectionFactory cm = ConnectionFactory.getInstance();
	private Properties queries = SQLQueriesProvider.getInstance().getQueries();
	
	@Override
	public int deleteSegmentController(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<SegmentController> getAllSegmentControllers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SegmentController getSegmentController(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertSegmentController(SegmentController segmentController) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSegmentController(SegmentController segmentController) {
		// TODO Auto-generated method stub
		return 0;
	}


}
