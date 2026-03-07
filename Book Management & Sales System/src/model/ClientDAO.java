package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.DBConnect;

public class ClientDAO implements IClientDAO{
	
	@Override
	public boolean login(ClientDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from client where clientNo=? and clientPassword=?";
		
		con = DBConnect.getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dto.getClientNo());
		pstmt.setString(2, dto.getClientPassword());
		
		rs = pstmt.executeQuery();
		boolean res = rs.next();
		DBConnect.close(con,pstmt,rs);
		
		if(res)
			return true;
		else
			return false;
	}

	@Override
	public Vector<ClientDTO> getAllCLient() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(ClientDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ClientDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ClientDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}