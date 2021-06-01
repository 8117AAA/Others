package _Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import _Bean.Blog;
import _DB.DBc;

public class Write {
	//写微博
	public boolean writeBlog(Blog blog) {
		String sql = "insert into blog (author,title,string) value('" +
				blog.getAuthor() + "','" + blog.getTitle() + "','" +
				blog.getString()+ "')";
//		System.out.println(sql);
		DBc dbc = new DBc();
		dbc.executeOther(sql);
		sql = "select * from blog where string='" + blog.getString() + "'";
		ResultSet rs = dbc.executeQuery(sql);
		try {
			if(rs.next())
				return true;
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbc.closeConnection();
		}
	}
}
