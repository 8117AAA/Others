<%@ page language="java" contentType="text/html; charset=UTF-8" import="_Bean.Blog,java.util.ArrayList"
    pageEncoding="UTF-8"%>
    <%
    
    ArrayList<Blog> blogs = (ArrayList<Blog>)session.getAttribute("blogs");
    for( Blog blog:blogs){
    	out.println("<section class=\"post\">");
    	out.println("<header class=\"post-header\">");
    	out.println("<h2 class=\"post-title\">"+ blog.getTitle() +"</h2>");
    	out.println("<p class=\"post-meta\">");
    	out.println("By <a class=\"post-author\" href=\"#\">"+ blog.getAuthor() +"</a> in " + blog.getBlogtime() + " <a href=\"DelServlet?time=" + blog.getBlogtime() +  "\">删除</a>");
    	out.println("</p>");
    	out.println("</header>");
    	out.println("<div class=\"post-description\">");
    	out.println("<p>");
    	out.println(blog.getString());
    	out.println("</p>");
        out.println("</div>");
        out.println("</section>");
    }
    
    %>
