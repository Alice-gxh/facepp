package com.ikohoo.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import com.ikohoo.domain.Employee;
import com.ikohoo.factory.BasicFactory;
import com.ikohoo.service.EmplService;

/**
 * Servlet implementation class RegEmpServlet
 */
@WebServlet("/RegEmpServlet")
public class RegEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegEmpServlet() {
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
		try {
			DateConverter dateConverter = new DateConverter(null);
			dateConverter
					.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd" });
			ConvertUtils.register(dateConverter, Date.class);

			BeanUtils.populate(emp, request.getParameterMap());
			System.out.println("emp id: "+ emp.getId());
			service.addEmployee(emp);
			System.out.println(emp);

			request.setAttribute("employee", emp);
			request.setAttribute("rstMsg", "注册成功！");
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
			return;
		} catch (Exception e) {
			request.setAttribute("rstMsg", "注册失败！");
			e.printStackTrace();
		}
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
