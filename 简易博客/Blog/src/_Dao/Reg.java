package _Dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import _Bean.User;
import _DB.DBc;


public class Reg {
	
	//检查name是否已经存在
	public boolean checkname(User user) {
		String sql = "select * from user where name='" + user.getName() + "'";
		DBc dbconn = new DBc();
		ResultSet rs = dbconn.executeQuery(sql);
		try {
			if(!rs.next())
				return true;
			return false;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return false;
		}finally {
			dbconn.closeConnection();
		}
	}
	//检查username是否已经存在
		public boolean checkusername(User user) {
			String sql = "select * from user where username='" + user.getUsername() + "'";
			DBc dbconn = new DBc();
			ResultSet rs = dbconn.executeQuery(sql);
			try {
				if(!rs.next())
					return true;
				return false;
			}catch(SQLException ex) {
				ex.printStackTrace();
				return false;
			}finally {
				dbconn.closeConnection();
			}
		}
	
	public boolean insertUser(User user) {
		String sql = "insert into user (username,name,password)"
				+ "values ('" + user.getUsername() + "','"
				+ user.getName() + "','"
				+ user.getPassword() + "')";
		
		DBc dbconn = new DBc();
		dbconn.executeOther(sql);
		dbconn.closeConnection();
		
		//用Login类中的方法来检查用户是否添加成功
		Login logindao = new Login();
		String checkUserFlag = logindao.checkUser(user);
		if(checkUserFlag != null) {
			user.setUsername(checkUserFlag);
			return true;
		}
		else {
			return false;
		}
	}
}
