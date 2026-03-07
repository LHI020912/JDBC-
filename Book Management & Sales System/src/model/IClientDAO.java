package model;

import java.util.Vector;

public interface IClientDAO {
	// CRUD
	public Vector<ClientDTO> getAllCLient() throws Exception;
	public boolean insert(ClientDTO dto) throws Exception;
	public boolean update(ClientDTO dto) throws Exception;
	public boolean delete(ClientDTO dto) throws Exception;
	public boolean login(ClientDTO dto) throws Exception;
	// 로그아웃은 연결을 끊기만 하면 돼서 구현X

}
