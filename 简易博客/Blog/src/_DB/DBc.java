package _DB;


import java.sql.*;

public class DBc {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	//连接数据库
	public void getConnection() {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";		//jdbc_driver
		final String DB_URL = "jdbc:mysql://localhost:3306/blog";				//jdbc_url
		final String USER = "root";								//username
		final String PASS = "root";							//password
		
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//执行查询语句，传入SQL语句
	public ResultSet executeQuery(String sql) {
		getConnection();//获取数据库连接
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		}catch(SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	//专门用于执行增删改操作
	public void executeOther(String sql) {
		getConnection();//获取数据库连接
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	//关闭连接
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
