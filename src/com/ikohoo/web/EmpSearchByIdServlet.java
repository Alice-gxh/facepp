package com.ikohoo.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ikohoo.domain.Employee;
//import com.ikohoo.domain.Product;
import com.ikohoo.factory.BasicFactory;
import com.ikohoo.service.EmplService;
//import com.ikohoo.service.ProdService;

/**
 * Servlet implementation class EmpSearchByIdServlet
 */
@WebServlet("/EmpSearchByIdServlet")
public class EmpSearchByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpSearchByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmplService service = BasicFactory.getFactory().getInstance(
				EmplService.class);
		Employee emp = new Employee();
		emp = service.findEmplById(request.getParameter("id"));
		String json = "{id:'" + emp.getId() + "',name:'"
				+ emp.getName() + "',password:'" + emp.getPassword()
				+ "'}";
		System.out.println("msg:" + json);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
