package com.ikohoo.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

public class EncodeFilter implements Filter {
	private FilterConfig config = null;
	private ServletContext context = null;
	private String encode = null;
	//static Logger logger = Logger.getLogger(EncodeFilter.class);

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 解决响应乱码
		response.setCharacterEncoding(encode);
		response.setContentType("text/html;charset="+encode);
		// 请求乱码问题
		chain.doFilter(new MyHttpServletRequest((HttpServletRequest) request), response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		this.context = filterConfig.getServletContext();
		this.encode = context.getInitParameter("encode");
		
	}

	class MyHttpServletRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request = null;
		private boolean isNotEncode = true;
		
		public MyHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public String getParameter(String name) {
			return getParameterValues(name)==null?null:getParameterValues(name)[0];
		}

		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			try{
				if (request.getMethod().equalsIgnoreCase("POST")){
					request.setCharacterEncoding(encode);
					return request.getParameterMap();
				} else if (request.getMethod().equalsIgnoreCase("GET")){
					Map<String, String[]> map = request.getParameterMap();
					if(isNotEncode) {
						for (Map.Entry<String, String[]> entry: map.entrySet()){
							String[] vs = entry.getValue();
							for (int i=0; i<vs.length; i++) {
								vs[i] = new String(vs[i].getBytes("iso8859-1"), encode);
							}
						}
						isNotEncode = false;
					}
					return map;
				}
				return super.getParameterMap();
			}
			catch (Exception e) {
				e.printStackTrace();
				//logger.error(e);
				throw new RuntimeException();
			}
		}
		
	}
}
