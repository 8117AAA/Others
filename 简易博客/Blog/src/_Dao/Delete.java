package _Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import _Bean.Blog;
import _DB.DBc;

public class Delete {
	public void DeleteBlog(String time,String author) {
		String sql = "delete from blog where blogtime='" + time + "' and author='" + author + "'" ;
		System.out.println(sql);
		DBc dbc = new DBc();
		dbc.executeOther(sql);
	}
}
