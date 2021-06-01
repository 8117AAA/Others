package _Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerInfo
 */
@WebServlet("/ServerInfo")
public class ServerInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().write("<br> java版本号：" + System.getProperty("java.version")); // java版本号 
		response.getWriter().write("<br> Java提供商名称：" + System.getProperty("java.vendor")); // Java提供商名称 
		response.getWriter().write("<br> Java提供商网站：" + System.getProperty("java.vendor.url")); // Java提供商网站 
		response.getWriter().write("<br> jre目录：" + System.getProperty("java.home")); // Java，哦，应该是jre目录 
		response.getWriter().write("<br> Java虚拟机规范版本号：" + System.getProperty("java.vm.specification.version")); // Java虚拟机规范版本号 
		response.getWriter().write("<br> Java虚拟机规范提供商：" + System.getProperty("java.vm.specification.vendor")); // Java虚拟机规范提供商 
		response.getWriter().write("<br> Java虚拟机规范名称：" + System.getProperty("java.vm.specification.name")); // Java虚拟机规范名称 
		response.getWriter().write("<br> Java虚拟机版本号：" + System.getProperty("java.vm.version")); // Java虚拟机版本号 
		response.getWriter().write("<br> Java虚拟机提供商：" + System.getProperty("java.vm.vendor")); // Java虚拟机提供商 
		response.getWriter().write("<br> Java虚拟机名称：" + System.getProperty("java.vm.name")); // Java虚拟机名称 
		response.getWriter().write("<br> Java规范版本号：" + System.getProperty("java.specification.version")); // Java规范版本号 
		response.getWriter().write("<br> Java规范提供商：" + System.getProperty("java.specification.vendor")); // Java规范提供商 
		response.getWriter().write("<br> Java规范名称：" + System.getProperty("java.specification.name")); // Java规范名称 
		response.getWriter().write("<br> Java类版本号：" + System.getProperty("java.class.version")); // Java类版本号 
		response.getWriter().write("<br> Java类路径：" + System.getProperty("java.class.path")); // Java类路径 
		response.getWriter().write("<br> Java lib路径：" + System.getProperty("java.library.path")); // Java lib路径 
		response.getWriter().write("<br> Java输入输出临时路径：" + System.getProperty("java.io.tmpdir")); // Java输入输出临时路径 
		response.getWriter().write("<br> Java编译器：" + System.getProperty("java.compiler")); // Java编译器 
		response.getWriter().write("<br> Java执行路径：" + System.getProperty("java.ext.dirs")); // Java执行路径 
		response.getWriter().write("<br> 操作系统名称：" + System.getProperty("os.name")); // 操作系统名称 
		response.getWriter().write("<br> 操作系统的架构：" + System.getProperty("os.arch")); // 操作系统的架构 
		response.getWriter().write("<br> 操作系统版本号：" + System.getProperty("os.version")); // 操作系统版本号 
		response.getWriter().write("<br> 文件分隔符：" + System.getProperty("file.separator")); // 文件分隔符 
		response.getWriter().write("<br> 路径分隔符：" + System.getProperty("path.separator")); // 路径分隔符 
		response.getWriter().write("<br> 直线分隔符：" + System.getProperty("line.separator")); // 直线分隔符 
		response.getWriter().write("<br> 操作系统用户名：" + System.getProperty("user.name")); // 用户名 
		response.getWriter().write("<br> 操作系统用户的主目录：" + System.getProperty("user.home")); // 用户的主目录 
		response.getWriter().write("<br> 当前程序所在目录：" + System.getProperty("user.dir")); // 当前程序所在目录
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
