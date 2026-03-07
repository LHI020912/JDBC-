package BooksaleView;

import java.util.Scanner;

import controller.BooksaleController;

public class BooksaleDelete {
	BooksaleController controller = BooksaleController.getinstance();
	
	public void delete(Scanner sc) {
		System.out.println("\n*************************");
		System.out.print("취소할 주문번호 입력: ");
		String bsNo = sc.nextLine();
		
		controller.delete(bsNo);
	}

}
