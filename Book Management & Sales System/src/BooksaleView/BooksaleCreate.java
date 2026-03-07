package BooksaleView;

import java.util.Scanner;

import controller.BooksaleController;
import model.BooksaleDTO;

//3번 행동지시
public class BooksaleCreate {
	BooksaleController controller = BooksaleController.getinstance();
	
	public void insert(Scanner sc) {
		String clientNo, bookNo;
		int bsQty;
		
		System.out.println("\n*************************");
		System.out.println("도서 구매 정보 입력");
		System.out.println("***************************");

		System.out.print("고객번호 입력 : ");	
		clientNo = sc.nextLine();
		System.out.print("도서번호 입력 : ");		
		bookNo = sc.nextLine();
		System.out.print("구매수량 입력 : ");		
		bsQty = Integer.parseInt(sc.nextLine());

		System.out.println("***************************");
		
		BooksaleDTO dto = new BooksaleDTO();
        dto.setBsQty(bsQty);
        dto.setClientNo(clientNo);
        dto.setBookNo(bookNo);
        
		controller.insert(dto);
	}
}
