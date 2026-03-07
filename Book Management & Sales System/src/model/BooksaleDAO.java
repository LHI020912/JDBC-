package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnect;

// 1번 sql실행

public class BooksaleDAO implements IBooksaleDAO{
	@Override // 구매
	public boolean create(BooksaleDTO dto) throws Exception {
		Connection con = null;
		con = DBConnect.getConnection();
		String sql = "INSERT INTO BOOKSALE (bsNo, bsDate, bsQty, clientNo, bookNo) VALUES (seq_bs.NEXTVAL, SYSDATE, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, dto.getBsQty());
        pstmt.setString(2, dto.getClientNo());
        pstmt.setString(3, dto.getBookNo());
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		
		if(result>0)
			return true;
		else
			return false;
	}

	@Override // 재고
	public boolean stock(BooksaleDTO dto) throws Exception {
		Connection con = null;
		con = DBConnect.getConnection();
		String sql ="UPDATE BOOK SET BOOKSTOCK = BOOKSTOCK -(?) WHERE BOOKNO =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, dto.getBsQty());
		pstmt.setString(2, dto.getBookNo());
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		
		if(result>0)
			return true;
		else
			return false;
	}
	

	@Override // 구매내역 조회
	public boolean read(BooksaleDTO dto) throws Exception {
		Connection con = DBConnect.getConnection();
		String sql ="SELECT * FROM BOOKSALE WHERE bsNo = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getBsNo());
		
		ResultSet rs = pstmt.executeQuery();
		boolean result = false;
		if(rs.next()) {
	        dto.setBsDate(rs.getString("bsDate"));
	        dto.setBsQty(rs.getInt("bsQty"));
	        dto.setClientNo(rs.getString("clientNo"));
	        dto.setBookNo(rs.getString("bookNo"));
	        result = true;
		}
		rs.close();
		pstmt.close();
		return result;
	}

	@Override // 구매내역 수정
	public boolean update(BooksaleDTO dto) throws Exception {
		Connection con = DBConnect.getConnection();
		// 구매 번호를 기준으로 수량 수정
		String sql= "UPDATE BOOKSALE SET BSQTY =? WHERE TRIM(BSNO) =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1,dto.getBsQty());		// 수량
		pstmt.setString(2, dto.getBsNo());	// 구매번호
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		
		return result>0;
	}
	
	@Override // 재고 수정
	public boolean stockUpdate(String bookNo, int diffQty) throws Exception {
		Connection con = DBConnect.getConnection();
		String sql = "UPDATE BOOK SET BOOKSTOCK = BOOKSTOCK -? WHERE BOOKNO=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, diffQty);
		pstmt.setString(2, bookNo);
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result>0;
	}

	@Override // 구매내역 취소
	public boolean delete(String bsNo) throws Exception {
		Connection con = DBConnect.getConnection();
		String sql = "DELETE FROM BOOKSALE WHERE bsNo =?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bsNo);
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result>0;
	}

	@Override // 재고 취소
	public boolean stockRestore(String bookNo, int qty) throws Exception {
		Connection con = DBConnect.getConnection();
		String sql = "UPDATE BOOK SET BOOKSTOCK = BOOKSTOCK + ? WHERE BOOKNO = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, qty);
		pstmt.setString(2, bookNo);
		
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result>0;
	}
	
}
