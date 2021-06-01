package _Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import _Bean.Blog;
import _DB.DBc;

public class Home {
	public ArrayList<Blog> getAllBlog(){
		String sql = "Select * from blog";
		DBc dbc = new DBc();
		ResultSet rs = dbc.executeQuery(sql);
		ArrayList<Blog> blogs = new ArrayList<Blog>();
		try {
			int rawNum = rs.getMetaData().getColumnCount();
			if(rawNum == 0)
				return null;
			while(rs.next()) {
				Blog blog = new Blog();
				blog.setAuthor(rs.getString("author"));
				blog.setTitle(rs.getString("title"));
				blog.setString(rs.getString("string"));
				blog.setBlogTime(rs.getString("blogtime"));
				blogs.add(blog);
			}
			return blogs;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			dbc.closeConnection();
		}
	}
}
