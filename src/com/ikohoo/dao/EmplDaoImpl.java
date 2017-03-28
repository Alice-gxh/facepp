package com.ikohoo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import com.ikohoo.domain.Employee;
import com.ikohoo.domain.Recognize;
import com.ikohoo.domain.Register;
import com.ikohoo.util.DaoUtils;

public class EmplDaoImpl implements EmplDao {
	//static Logger logger = Logger.getLogger(EmplDaoImpl.class);

	@Override
	public void addEmpl(Employee empl) {
		String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, empl.getId(), empl.getName(),
					empl.getPassword(), empl.getNickname(),
					empl.getCellphone(), empl.getEmail(), empl.getIdcard(),
					empl.getGender(), empl.getBirthday(), empl.getDept(),
					empl.getRole(),
					empl.getState(), new SimpleDateFormat("YYYY-MM-dd").format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e.getStackTrace(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public Employee findEmplById(String id) {
		String sql = " SELECT * FROM employee WHERE id=? ";

		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			System.out.println("DATA-SOURCE: "+DaoUtils.getSource().toString());
			System.out.println("DATA-SOURCE: "+DaoUtils.getConn().toString());
			return runner.query(sql, new BeanHandler<Employee>(Employee.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e.getStackTrace(),e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Employee> getAllEmployee() {
		String sql = " SELECT * FROM employee ";

		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Employee>(
					Employee.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Register> getRegists(String userId) {
		String sql = " SELECT * FROM regist WHERE user_id=? ";

		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<Register>(
					Register.class),
					userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addRecognize(String userid, String photoid, String location) {
		String sql = "INSERT INTO recognize VALUES(null,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, 
					userid,
					photoid,
					location,
					new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date())
					);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Recognize> getRecgLog(int pagesize, int curpage) {
		int offset = (curpage - 1) * pagesize;
		String sql = " SELECT r.*, e.name user_name, e.dept FROM recognize r LEFT JOIN employee e ON r.user_id=e.id ORDER BY r.id DESC LIMIT ?, ? ";

		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql,
					new BeanListHandler<Recognize>(Recognize.class), offset,
					pagesize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getRecgLogCount() {
		String sql = "SELECT COUNT(*) FROM recognize";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long) runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error(e);
			throw new RuntimeException();
		}
	}

}
