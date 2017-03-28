package com.ikohoo.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javafx.application.Application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
//import org.apache.commons.fileupload.ProgressListener;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sun.awt.AppContext;

import com.ikohoo.domain.Employee;
import com.ikohoo.factory.BasicFactory;
import com.ikohoo.service.EmplService;
import com.ikohoo.util.IOUtils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmplService service = BasicFactory.getFactory().getInstance(
				EmplService.class);
		Map<String, String> params = new HashMap<String, String>();
		//System.out.println("aaaaaaaaaaaa");
		
//		try {
//			DateConverter dateConverter = new DateConverter(null);
//			dateConverter.setPatterns(new String[]{"yyyy-MM-dd","yyyy/MM/dd"});
//			ConvertUtils.register(dateConverter, Date.class);	
//			// 封装校验数据
//			Employee emp = new Employee();
//			BeanUtils.populate(emp, request.getParameterMap());
//			
//			System.out.println("ADD-EMPLOYEE: "+emp.toString());
//			// 调用service 添加员工
//			service.addEmployee(emp);
//			
//			// 返回页面
//			response.getWriter().write("success，return in 1 seconds");
//			response.setHeader("Refresh", "1;url=emplAdd.jsp");
//			return;
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}		
		
		
		
		response.setContentType("text/html;charset=utf-8");
		try{
			//1.创建工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(100*1024);
			factory.setRepository(new File("temp"));
			System.out.println(factory.getRepository().getAbsolutePath());
			
			//2.生产文件上传核心类
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			
			//--检查是否是正确的文件上传表单
			if(!fileUpload.isMultipartContent(request)){
				throw new RuntimeException("请用正确的表单进行上传!");
			}
			//--设置文件上传的大小限制
//			fileUpload.setFileSizeMax(1024*1024*100);//单个文件不大于10M
//			fileUpload.setSizeMax(1024*1024*100);//总大小不大于100M
			
			//--设置编码集,解决上传文件名的乱码问题
			fileUpload.setHeaderEncoding("utf-8");
			
//			//--设置文件上传监听
//			fileUpload.setProgressListener(new ProgressListener(){
//				Long beginTime = System.currentTimeMillis();
//				public void update(long bytesRead, long contentLength, int items) {
//					BigDecimal br = new BigDecimal(bytesRead).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
//					BigDecimal cl = new BigDecimal(contentLength).divide(new BigDecimal(1024),2,BigDecimal.ROUND_HALF_UP);
//					System.out.print("当前读取的是第"+items+"个上传项,总大小"+cl+"KB,已经读取"+br+"KB");
//					//剩余字节数
//					BigDecimal ll = cl.subtract(br);
//					System.out.print("剩余"+ll+"KB");
//					//上传百分比
//					BigDecimal per = br.multiply(new BigDecimal(100)).divide(cl,2,BigDecimal.ROUND_HALF_UP);
//					System.out.print("已经完成"+per+"%");
//					//上传用时
//					Long nowTime = System.currentTimeMillis();
//					Long useTime = (nowTime - beginTime)/1000;
//					System.out.print("已经用时"+useTime+"秒");
//					//上传速度
//					BigDecimal speed = new BigDecimal(0);
//					if(useTime!=0){
//						speed = br.divide(new BigDecimal(useTime),2,BigDecimal.ROUND_HALF_UP);
//					}
//					System.out.print("上传速度为"+speed+"KB/S");
//					//大致剩余时间
//					BigDecimal ltime = new BigDecimal(0);
//					if(!speed.equals(new BigDecimal(0))){
//						ltime = ll.divide(speed,0,BigDecimal.ROUND_HALF_UP);
//					}
//					System.out.print("大致剩余时间为"+ltime+"秒");
//					
//					System.out.println();
//				}
//				
//			});
//			
			//3.利用文件上传核心类解析request
			List<FileItem> list = fileUpload.parseRequest(request);
			//4.遍历所有的FileItem
			for(FileItem item : list){
				if(item.isFormField()){
					//当前是一个普通的字段项
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					params.put(name, value);
					System.out.println(name+":"+value);
				}else{
					//当前是一个文件上传项
//					String filename = item.getName();
//					String uuidName = UUID.randomUUID().toString()+"_"+filename;
//					System.out.println("uuid...."+uuidName);
//					
//					int hash = uuidName.hashCode();
//					String hashStr = Integer.toHexString(hash);
//					char [] hss = hashStr.toCharArray();
//					String path = request.getServletContext().getRealPath("/");//RegisterServlet.class.getResource("../").getPath()+"/WEB-INF/upload"; //RegisterServlet.class.getResource("/").getPath(); //this.getServletContext().getRealPath("upload");
//					System.out.println("path:"+path);
//					response.getWriter().write(path);
//				
//					
////					for(char c : hss){
////						path+="/"+c;
////					}
////					new File(path).mkdirs();
//					
//					InputStream in = item.getInputStream();
//					OutputStream out = new FileOutputStream(new File(path,uuidName));
//					
//					IOUtils.In2Out(in, out);
//					IOUtils.close(in, out);
//					
//					//--删除临时文件
//					item.delete();
				}
				
			}
			Employee emp = new Employee();
			emp.setId(params.get("id"));
			emp.setName(params.get("name"));
			emp.setDept(params.get("dept"));
			service.addEmployee(emp);
//			
//			
			response.getWriter().write("insert ok");
			//request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
//		}catch (FileSizeLimitExceededException e) {
//			response.getWriter().write("单个文件不超过10M,总大小不超过100M!");
//			return;
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
