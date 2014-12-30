package pl.edu.agh.ztb.mod2.dao.impl;

import java.util.List;

import org.sqlproc.engine.SqlEngineFactory;
import org.sqlproc.engine.SqlSessionFactory;

import pl.edu.agh.ztb.mod2.dao.FixturesDao;

public class FixturesDaoPgsql implements FixturesDao {
	
	private SqlEngineFactory sqlFactory;
	private SqlSessionFactory sessionFactory;

	public FixturesDaoPgsql(SqlEngineFactory sqlFactory,
			SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.sqlFactory = sqlFactory;
	}

	public void test() {
       	@SuppressWarnings("unchecked")
		List<Object> res = sessionFactory.getSqlSession().createSqlQuery("SELECT VERSION()").list(null);

        for (Object x : res) {
            System.out.println(x);
        }
	}
}
