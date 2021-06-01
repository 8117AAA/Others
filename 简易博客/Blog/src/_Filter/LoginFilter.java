package _Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _Bean.User;

public class LoginFilter implements Filter {
  
    public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user != null) {			
			chain.doFilter(request, response);
			return;
		}
		response.sendRedirect("login.html");
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	

}
