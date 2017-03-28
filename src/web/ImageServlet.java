package web;

import ikohoo.Base64Code;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import sun.misc.BASE64Decoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String imgStr = request.getParameter("imgStr");
		final String JPG = "image/jpg;charset=GB2312";
		System.out.println("in imageservlet..."+imgStr);
		
		GenerateImage(request, response);
		
		
//		String path = this.getServletContext().getRealPath("/");
//		System.out.println("path: "+path);
//		
//		response.setContentType(JPG);
//		OutputStream out = response.getOutputStream();
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(image);
		
		
		//String json = "{img:img}";
		//response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static String GenerateImage(HttpServletRequest request, HttpServletResponse response) { // 对字节数组字符串进行Base64解码并生成图片
		
		String imgStr = request.getParameter("imgStr");
		System.out.println("gen image with: " + imgStr);
		response.setContentType("image/jpeg");
		
		ServletOutputStream out = null; 
		
		if (imgStr == null) // 图像数据为空
		{
			System.out.println("img str: null");
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			// out = servletOutputStream;//new FileOutputStream(imgFilePath);
			out = response.getOutputStream();
			out.write(b);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
