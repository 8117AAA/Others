package _Servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _Bean.User;
import _Dao.Login;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		Login logindao = new Login();
		String loginFlag = logindao.checkUser(user);
		
		if(loginFlag != null) {
			user.setUsername(loginFlag);
			user.setName(logindao.getName(user));
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("HomeServlet");
		}
		else {
			request.getRequestDispatcher("login.html?1").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}