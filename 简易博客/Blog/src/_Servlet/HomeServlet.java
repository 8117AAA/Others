package _Servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _Bean.Blog;
import _Dao.Home;
import _Dao.TimeSort;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Home home = new Home();
		ArrayList<Blog> blogs =  home.getAllBlog();
		
		//ArrayList结果按时间排序
		TimeSort timesort = new TimeSort();
		Collections.sort(blogs, timesort);
		session.setAttribute("blogs", blogs);
		response.sendRedirect("Home.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
