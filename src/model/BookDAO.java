package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.DBConnect;

// 1번 sql실행

public class BookDAO implements IBookDAO {

	@Override // 입력
	public boolean insert(BookDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql ="insert into book values(?,?,?,?,?,?,?)";
		
		con = DBConnect.getConnection(); // throws문으로 오류 던져주는 거 했으면 굳이 try-catch 안쪽에 안 해도 됨.
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, dto.getBookNo());
		pstmt.setString(2, dto.getBookName());
		pstmt.setString(3, dto.getBookAuthor());
		pstmt.setInt(4, dto.getBookPrice());
		pstmt.setString(5, dto.getBookDate());
		pstmt.setInt(6, dto.getBookStock());
		pstmt.setString(7, dto.getPubNo());
		
		int result = pstmt.executeUpdate();
		
		DBConnect.close(con, pstmt);
		
		if (result >0)
			return true;
		else
			return false;
	}
	
	@Override // 조회
	public Vector<BookDTO> getAllBook() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<BookDTO> dataSet = null;
		
		String sql = "select * from book order by bookNo";
		
		con = DBConnect.getConnection();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		dataSet = new Vector<BookDTO>();
		
		while(rs.next()) {
			dataSet.add(new BookDTO(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getDate(5).toString(), // YYYY-MM-DD만 출력
					rs.getInt(6),
					rs.getString(7)));
		}
		DBConnect.close(con,pstmt,rs);
		return dataSet;
	}

	@Override // 수정
	public boolean update(BookDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		con = DBConnect.getConnection();
		
		String sql = "update book set bookName=?, bookAuthor=?,"
				+ "bookPrice=?,bookDate=?, bookStock=? where bookNo=?";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, dto.getBookName());
		pstmt.setString(2, dto.getBookAuthor());
		pstmt.setInt(3, dto.getBookPrice());
		pstmt.setString(4, dto.getBookDate());
		pstmt.setInt(5, dto.getBookStock());
		pstmt.setString(6, dto.getBookNo());
		
		int result = pstmt.executeUpdate();
		DBConnect.close(con,pstmt);
		
		if(result >0)
			return true;
		else
			return false;
	}

	@Override // 삭제
	public boolean delete(BookDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from book where bookNo=?";
		con = DBConnect.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getBookNo());
		
		int result = pstmt.executeUpdate();
		DBConnect.close(con,pstmt);
		
		if(result > 0)
			return true;
		else
			return false;
	}
}