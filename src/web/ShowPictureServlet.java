package web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class ShowPictureServlet
 */
@WebServlet("/ShowPictureServlet")
public class ShowPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String GIF = "image/gif;charset=GB2312";
	private static final String JPG = "image/jpeg;charset=GB2312";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowPictureServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String spec = request.getParameter("spec");
		String imagePath = null;
		// 输出图片的类型的标志
		int int_spec = Integer.parseInt(spec);
		if (int_spec == 1) {
			imagePath = "/jfgg/b1.jpg"; // 图片相对web应用的位置
		} else {
			imagePath = "/jfgg/b2.gif";
		}
		OutputStream output = response.getOutputStream();// 得到输出流

		if (imagePath.toLowerCase().endsWith(".jpg"))// 使用编码处理文件流的情况：
		{
			response.setContentType(JPG);// 设定输出的类型
			// 得到图片的真实路径
			imagePath = getServletContext().getRealPath(imagePath);
			InputStream imageIn = new FileInputStream(new File(imagePath));
			// 得到输入的编码器，将文件流进行jpg格式编码
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn); // 得到编码后的图片对象
			BufferedImage image = decoder.decodeAsBufferedImage(); // 得到输出的编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
			encoder.encode(image);// 对图片进行输出编码
			imageIn.close();// 关闭文件流
		} else

		if (imagePath.toLowerCase().endsWith(".gif"))// 不使用编码处理文件流的情况：
		{
			response.setContentType(GIF);
			ServletContext context = getServletContext();// 得到背景对象
			InputStream imageIn = context.getResourceAsStream(imagePath);// 文件流
			BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
			BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
			
			byte data[] = new byte[4096];// 缓冲字节数
			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
			
			bis.close();
			bos.flush();// 清空输出缓冲流
			bos.close();
		}
		output.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
