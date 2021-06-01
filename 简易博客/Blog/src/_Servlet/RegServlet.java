package _Servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _Bean.User;
import _Dao.Reg;


public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setName(new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8"));
		user.setPassword(request.getParameter("password"));
		
		Reg reg = new Reg();
		PrintWriter out = response.getWriter();
		if(reg.checkname(user)) {
			if(reg.insertUser(user)) {
				response.sendRedirect("RegSuccess.html");
			}
			else {
				out.println("<a href=\"register.jsp\">注册失败，请重试！</a>");
			}
		}
		else {
			out.println("<a href=\"register.jsp\">昵称已存在，请重试！</a>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
