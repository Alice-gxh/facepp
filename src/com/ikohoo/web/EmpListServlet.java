package com.ikohoo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ikohoo.domain.Employee;
import com.ikohoo.factory.BasicFactory;
import com.ikohoo.service.EmplService;

/**
 * Servlet implementation class EmpListServlet
 */
@WebServlet("/EmpListServlet")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmplService service = BasicFactory.getFactory().getInstance(EmplService.class);
		//1 调用service中的方法，查询所有客户
		List<Employee> list = service.getAllEmployee();
		System.out.println("emplsit");
		
		for (Employee emp: list) {
			System.out.println(emp);
		}
		//2 将查找到的信息存入request域，请求转发到EmpList.jsp
		request.setAttribute("list", list);
		request.getRequestDispatcher("users.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
