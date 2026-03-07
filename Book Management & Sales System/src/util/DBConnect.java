package util;
/*
 * db 연결 및 종료 관리
 * getConnection(): db 연결(sesstion) 관리
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	public static Connection getConnection() {
		Connection con = null;

		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "C##SQL_USER";
			String pwd = "1234";
			
			con = DriverManager.getConnection(url,user,pwd);
			
			if(con !=null)
				System.out.println("db연결성공");
			else
				System.out.println("db연결실패");
			
			return con;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	// 자원 CLOSE 메소드 * 오버라이딩
	// 외부 자원 사용 메소드 : 반드시 예외 처리 필요
	// 2. 3개 자원 close
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 2개 자원 close
	public static void close(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if(con != null) {
				con.close();
				con = null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3-1. 2개 자원 close
	public static void close(PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 4. 1개 자원 close
	public static void close(Connection con) {
		try {
			if(con != null) {
				con.close();
				con = null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. 1개 자원 close
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
