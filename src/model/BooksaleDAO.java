package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// 1번 sql실행

public class BooksaleDAO implements IBooksaleDAO{
	@Override // 구매
	public boolean create(BooksaleDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		con = pstmt.getConnection();
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, sql);
		
		return false;
	}

	@Override // 구매내역 조회
	public boolean read(BooksaleDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		return false;
	}

	@Override // 구매내역 수정
	public boolean update(BooksaleDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		return false;
	}

	@Override // 구매내역 취소
	public boolean delete(BooksaleDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		return false;
	}

	@Override
	public boolean stock(BooksaleDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	

}
