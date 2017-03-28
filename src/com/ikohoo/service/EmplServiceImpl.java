package com.ikohoo.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.ikohoo.dao.EmplDao;
import com.ikohoo.domain.Employee;
import com.ikohoo.domain.Page;
import com.ikohoo.domain.Recognize;
import com.ikohoo.domain.Register;
import com.ikohoo.factory.BasicFactory;

public class EmplServiceImpl implements EmplService {

	EmplDao dao = BasicFactory.getFactory().getInstance(EmplDao.class);
	//static Logger logger = Logger.getLogger(EmplService.class);
	
	@Override
	public void addEmployee(Employee empl) {
		// 1. 检查用户id是否存在
		if (dao.findEmplById(empl.getId()) != null) {
			//logger.error("工号已经存在！");
			throw new RuntimeException("工号已经存在！");
		}
		// 2. 调用dao的方法，添加员工
		dao.addEmpl(empl);
		// 3.
	}


	@Override
	public List<Employee> getAllEmployee() {
		return dao.getAllEmployee();
	}

	@Override
	public Employee findEmplById(String id) {
		return dao.findEmplById(id);
	}

	@Override
	public Employee login(String username, String password) {
		Employee emp = dao.findEmplById(username);
		if (emp.getPassword().equals(password))
			return emp;
		else return null;

	}


	@Override
	public List<Register> getRegists(String userId) {
		return dao.getRegists(userId);
	}


	@Override
	public void addRecognize(String userid, String photoid, String location) {
		dao.addRecognize(userid, photoid, location);
	}


	@Override
	public Page getRecgLogPage(int pagesize, int curpage) {
		Page page = new Page();
		int rowCount = 0;
		
		rowCount = dao.getRecgLogCount();
		page.setCurPage(curpage);
		page.setRowPerPage(pagesize);
		page.setRowCount(rowCount);
		return page;
	}


	@Override
	public List<Recognize> getRecgLog(int pagesize, int curpage) {
		
		return dao.getRecgLog(pagesize, curpage);
	}

}
