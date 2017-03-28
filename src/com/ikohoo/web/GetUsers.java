package com.ikohoo.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetUsers
 */
@WebServlet("/GetUsers")
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("in servlet" + GetUsers.class.getName());
		 response.setContentType("text/html;charset=GB2312");  
	        String url = request.getParameter("url");  
	        StringBuffer param = new StringBuffer();  
	        Enumeration<String> enu = request.getParameterNames();  
	        int total = 0;  
	        while(enu.hasMoreElements()){  
	            String name = (String)enu.nextElement();  
	            if(!name.equals("url")){  
	                if(total == 0){  
	                    param.append(name).append("=").append(URLEncoder.encode(request.getParameter(name),"UTF-8"));  
	                } else{  
	                    param.append("&").append(name).append("=").append(URLEncoder.encode(request.getParameter(name),"UTF-8"));  
	                }  
	                total++;  
	  
	            }  
	        }  
	        PrintWriter out = response.getWriter();  
	        if(url != null){  
	            URL connect = new URL(url.toString());  
	            URLConnection connection = connect.openConnection();  
	            connection.setDoOutput(true);  
	            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream());  
	            paramout.write(param.toString());  
	            paramout.flush();  
	            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GB2312"));  
	            String line;  
	            while((line = reader.readLine()) != null){  
	               out.println(line);  
	            }  
	            paramout.close();  
	            reader.close();  
	        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
