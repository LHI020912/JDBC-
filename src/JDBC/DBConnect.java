package sec01;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	public Connection getConnection() {
		Connection con = null;
		// 예외처리
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
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
