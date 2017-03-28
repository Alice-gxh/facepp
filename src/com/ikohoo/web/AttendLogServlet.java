package com.ikohoo.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ikohoo.domain.Page;
import com.ikohoo.domain.Recognize;
import com.ikohoo.factory.BasicFactory;
import com.ikohoo.service.EmplService;

/**
 * Servlet implementation class AttendLogServlet
 */
@WebServlet("/AttendLogServlet")
public class AttendLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmplService service = BasicFactory.getFactory().getInstance(EmplService.class);
		
		int pagesize = request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):15; // pagesize 每页的大小
		int curpage = request.getParameter("page")!=null?Integer.parseInt(request.getParameter("page")):1; // pages 第几页

		Page page = service.getRecgLogPage(pagesize, curpage);
		List<Recognize> list = service.getRecgLog(pagesize, curpage);
		
		request.setAttribute("page", page);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/attendLog.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
