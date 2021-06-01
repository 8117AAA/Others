package _Dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import _Bean.User;
import _DB.DBc;

public class Login {

	//登录验证
	public String checkUser(User user) {
		String sql = "select * from user where username='"
				+ user.getUsername() +"' and password='"
				+ user.getPassword() +"'";
		System.out.println("SQL: " + sql);//输出SQL语句，调试使用
		DBc dbconn = new DBc();
		ResultSet rs = dbconn.executeQuery(sql);
		try {
			if(rs.next())
				return rs.getString("username");
			return null;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}finally {
			dbconn.closeConnection();
		}
	}
	
	public String getName(User user) {
		String sql = "select * from user where username='" + user.getUsername() + "'";
		System.out.println("SQL: " + sql);
		DBc dbconn = new DBc();
		ResultSet rs = dbconn.executeQuery(sql);
		
		try {
			rs.next();
			return rs.getString("name");
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}finally {
			dbconn.closeConnection();
		}
	}
}