package controller;

import model.ClientDAO;
import model.ClientDTO;

public class ClientController {
	private static ClientController instance = new ClientController();
	ClientDAO dao = new ClientDAO();
	
	private ClientController() {}
	
	public static ClientController getinstance() {
		return instance;
	}
	
	public boolean login(String no, String pass) {
		try {
			ClientDTO dto = new ClientDTO();
			dto.setClientNo(no);
			dto.setClientPassword(pass);
			return dao.login(dto);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}