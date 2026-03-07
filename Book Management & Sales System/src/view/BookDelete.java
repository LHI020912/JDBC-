package view;
//3번 행동지시

import java.util.Scanner;
import controller.BookController;

public class BookDelete {
	BookController controller = BookController.getInstance();
	
	public void delete(Scanner sc) {
		String bookNo;
		
		System.out.println("\n*************************");
		System.out.println("도서정보 삭제");
		System.out.println("\n*************************");
		
		BookList bls = new BookList();
		bls.getAllBook();
		
		System.out.print("삭제할 도서번호 입력");
		bookNo=sc.nextLine();
		System.out.println("***************************");
		
		controller.delete(bookNo);
	}
}
