package com.dragon.blog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.dragon.blog.entity.FileUpload;

public class HandleUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String key = Integer.toString(request.hashCode());
		request.getSession().setAttribute("key", key);
		PrintWriter out = response.getWriter();
		String uploadFilePath = "X:\\Users\\Leezp\\Desktop\\Net_BigWork_2014110419_李宗佩\\Blog\\WebContent\\upload\\";
		FileUpload fu = new FileUpload();
		fu.setMap(request, key);//解析request
		Map<String,FileItem> files = fu.getFiles();
		String fileName = fu.getFileName(files.get("file"));
		File tempFile = new File("X:\\Users\\Leezp\\Desktop\\Net_BigWork_2014110419_李宗佩\\Blog\\WebContent\\upload\\");
		if(!tempFile.exists())
			tempFile.mkdirs();
		File file = new File(uploadFilePath,fileName);
		try {
			files.get("file").write(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<script>parent.uploadSuccess();</script>");
	}
}
