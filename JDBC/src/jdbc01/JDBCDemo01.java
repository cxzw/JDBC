package jdbc01;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCDemo01 {

	public static void main(String[] args) {
//		selectAll();
//		System.out.println(selectByUsernamePassword("xzw", "123"));
//		System.out.println(selectByUP("xzw", "123"));
//		insert("席子文牛逼", "123456");
//		delete(32);
//		update(50, "00000");
		transferAccounts("席子文", "xzw", 1000);
	}
	public static void selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from user");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
				System.out.println(rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password"));
			}
			
			
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			JDBCUtils.close(rs, stmt, con);
			
		}
		
	}
	
	public static boolean selectByUsernamePassword(String username, String password) {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/web01?serverTimezone=GMT%2B8";
			con = DriverManager.getConnection(url,"root","123456");
			
			stmt = con.createStatement();
			
			String sql = "select * from user where username = '"+username+"'and password = '"+password+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
		return false;
	}

	public static boolean selectByUP(String username, String password) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/web01?serverTimezone=GMT%2B8";
			con = DriverManager.getConnection(url,"root","123456");
			
			String sql = "select * from user where username = ? and password = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
		}
		
		return false;
	}
	
	public static void insert(String username, String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			
			String sql = "insert into user (username,password) values(?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			int result = stmt.executeUpdate();//返回值代表受到影响的行数
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			JDBCUtils.close(rs, stmt, con);
			
		}
	}

	public static void delete(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			
			String sql = "delete from user where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			
			int result = stmt.executeUpdate();//返回值代表受到影响的行数
			
			if(result > 0) {
				System.out.println("删除成功");
			}else {
				System.out.println("删除失败");
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			JDBCUtils.close(rs, stmt, con);
			
		}
	}

	public static void update(int id, String newPassword) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			
			String sql = "update user set password = ? where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, newPassword);
			stmt.setInt(2, id);
			
			int result = stmt.executeUpdate();//返回值代表受到影响的行数
			
			if(result > 0) {
				System.out.println("修改成功");
			}else {
				System.out.println("修改失败");
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			JDBCUtils.close(rs, stmt, con);
			
		}
	}
	
	public static void transferAccounts(String username1, String username2, int money) {
		Connection con = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			
			String sql = "update user set balance = balance - ? where username = ?";
			stmt1 = con.prepareStatement(sql);
			stmt1.setInt(1, money);
			stmt1.setString(2, username1);
			stmt1.executeUpdate();//返回值代表受到影响的行数
			
			sql = "update user set balance = balance + ? where username = ?";
			stmt1 = con.prepareStatement(sql);
			stmt1.setInt(1, money);
			stmt1.setString(2, username2);
			stmt1.executeUpdate();//返回值代表受到影响的行数
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			
			JDBCUtils.close(stmt2, stmt1, con);
			
		}
	}
	
}
