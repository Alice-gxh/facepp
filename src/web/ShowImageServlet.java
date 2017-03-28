package web;

import ikohoo.Base64Code;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowImageServlet
 */
@WebServlet("/ShowImageServlet")
public class ShowImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		showImage(request, response);
		//downloadImage(request, response);
	}

	private void downloadImage(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取图片绝对路径
		String path = this.getServletContext().getRealPath("/");
		File file = new File(path + "/img/myworkcard.jpg");
		// 设置头信息,内容处理的方式,attachment以附件的形式打开,就是进行下载,并设置下载文件的命名
		response.setHeader("Content-Disposition",
				"attachment;filename=" + file.getName());
		// 创建文件输入流
		FileInputStream is = null;
		ServletOutputStream out = null;
		try {
			is = new FileInputStream(file);
			// 响应输出流
			out = response.getOutputStream();
			// 创建缓冲区
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			is.close();
			out.flush();
			out.close();
		} catch (Exception e) {

		}

	}

	private void showImage(HttpServletRequest request,
			HttpServletResponse response) {
		// 读取方式
		// response.setContentType("application/zip");
		response.setContentType("image/jpeg");
		// 获取图片绝对路径
		String path = this.getServletContext().getRealPath("/");
		FileInputStream is = null;
		ServletOutputStream out = null;
		File file = new File(path + "/img/myworkcard.jpg");
		try {
			// 创建文件输入流
			is = new FileInputStream(file);
			// 响应输出流
			out = response.getOutputStream();
			// 创建缓冲区
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			
			//Base64Code.GenerateImage("");
		} catch (Exception e) {

		} finally {

			try {
				is.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
