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
import _Bean.User;
import _Dao.Select;
import _Dao.TimeSort;


public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//取得当前session
		User user = (User)session.getAttribute("user");
		Select select = new Select();
		ArrayList<Blog> blogs =  select.getBlogByUser(user);
		
		//ArrayList结果按时间排序
		TimeSort timesort = new TimeSort();
		Collections.sort(blogs, timesort);
		session.setAttribute("goalUser", user);
		session.setAttribute("blogs", blogs);
		response.sendRedirect("My.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
