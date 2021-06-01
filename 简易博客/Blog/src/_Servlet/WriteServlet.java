package _Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _Bean.Blog;
import _Bean.User;
import _Dao.Write;

public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public WriteServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Blog blog = new Blog();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		blog.setAuthor(user.getName());
		String title = new String(request.getParameter("title").getBytes("ISO-8859-1"),"utf-8");
		blog.setTitle(title);
		String string = new String(request.getParameter("string").getBytes("ISO-8859-1"),"utf-8");
		blog.setString(string);
		Write write = new Write();
		if(write.writeBlog(blog)) {
			response.sendRedirect("HomeServlet");
		}else {
			response.sendRedirect("Err.html");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
