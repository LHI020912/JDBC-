package controller;
//2번 정보지시

import model.BooksaleDAO;

public class BooksaleController {
	private static BooksaleController instance = new BooksaleController();
	BooksaleDAO dao = new BooksaleDAO();
	
	private BooksaleController() {}
	public static BooksaleController getinstance() {
		return instance;
	}
	
	
}
