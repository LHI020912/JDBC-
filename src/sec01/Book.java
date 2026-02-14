package sec01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Book {
	private static Scanner sc = new Scanner(System.in);
	static String year, name;
	static String sql = null;
	static int pr;
	
	public static void total(Connection con){
		
		try(PreparedStatement pstmt = con.prepareStatement("SELECT COUNT (*) FROM BOOK");
			ResultSet rs = pstmt.executeQuery()){
			
			if (rs.next())
				System.out.println("전체 도서 권수는 "+rs.getInt(1)+"권입니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void date(Connection con) {
		System.out.print("조회할 기준 년도를 입력해주세요: ");
		year = sc.nextLine();
		
		sql="SELECT * FROM BOOK WHERE TO_CHAR(BOOKDATE, 'YYYY') >= ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1, year);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.format("%-5s | %-15s | %-10s | %d원\n", 
		                rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public static void author(Connection con) {
		System.out.print("저자명을 입력해주세요: ");
		name = sc.nextLine();

		sql = "SELECT * FROM BOOK WHERE BOOKAUTHOR LIKE ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setString(1,"%"+ name+"%");
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				System.out.format("%-5s | %-15s | %-10s | %d원\n", 
		                rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void price(Connection con) {
		System.out.print("가격을 입력해주세요: ");
		pr = Integer.parseInt(sc.nextLine());

		sql = "SELECT * FROM BOOK WHERE BOOKPRICE >= ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, pr);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				System.out.format("%-5s | %-15s | %-10s | %d원\n", 
		                rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
