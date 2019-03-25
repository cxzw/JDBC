package jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	
	private static final String connectionURL = "jdbc:mysql://localhost:3306/web01?serverTimezone=GMT%2B8";
	private static final String username = "root";
	private static final String password = "123456";
		
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//使用什么驱动连接数据库
			return DriverManager.getConnection(connectionURL, username, password);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}
	public static void close(ResultSet rs,Statement stmt,Connection con) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(con);
	}
	
	public static void close(Statement stmt1,Statement stmt2,Connection con) {
		closeStatement(stmt1);
		closeStatement(stmt2);
		closeConnection(con);
	}
	
	private static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private static void closeStatement(Statement stmt) {
		try {
			if(stmt!=null)
				stmt.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	private static void closeConnection(Connection con) {
		try {
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
