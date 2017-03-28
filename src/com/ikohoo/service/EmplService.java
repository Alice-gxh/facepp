package com.ikohoo.service;

import java.util.List;

import com.ikohoo.domain.Employee;
import com.ikohoo.domain.Page;
import com.ikohoo.domain.Recognize;
import com.ikohoo.domain.Register;

public interface EmplService {

	/**
	 * 添加员工/员工入职
	 * @param empl
	 */
	void addEmployee(Employee empl);

	/**
	 * 员工登录系统
	 * @param username：用户名 password：密码
	 * @return  返回登录的员工 //   登录成功：RetCode.LoginSucc；密码错误：LoginErrPwd 用户名不存在: LoginNoUser
	 */
	Employee login(String username, String password);


	/**
	 * 列出所有员工
	 * @return
	 */
	List<Employee> getAllEmployee();

	/**
	 * 根据编号查找员工信息
	 * @param id
	 * @return 
	 */
	Employee findEmplById(String id);

	/**
	 * 通过用户ＩＤ获取注册信息
	 * @param userId
	 * @return
	 */
	List<Register> getRegists(String userId);

	void addRecognize(String userid, String photoid, String location);

	Page getRecgLogPage(int pagesize, int curpage);

	List<Recognize> getRecgLog(int pagesize, int curpage);

	
}
