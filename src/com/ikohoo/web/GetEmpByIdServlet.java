package com.ikohoo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ikohoo.domain.Employee;
import com.ikohoo.factory.BasicFactory;
import com.ikohoo.service.EmplService;

/**
 * Servlet implementation class GetEmpByIdServlet
 */
@WebServlet("/GetEmpByIdServlet")
public class GetEmpByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEmpByIdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EmplService service = BasicFactory.getFactory().getInstance(
				EmplService.class);
		Employee emp = new Employee();
		System.out.println("id:"+request.getParameter("id"));
		
		String json = null;
		emp = service.findEmplById(request.getParameter("id"));
		if (emp == null) {
			json = "{id:'0',name:'未注册',dept:'未知'}";
		} else {
			json = "{id:'" + emp.getId() + "',name:'" + emp.getName()
					+ "',dept:'" + emp.getDept() + "'}";
		}
		System.out.println(json);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
