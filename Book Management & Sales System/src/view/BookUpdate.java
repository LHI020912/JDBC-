package view;

import java.util.Scanner;

import controller.BookController;
import model.BookDTO;

public class BookUpdate {
	// 멤버필드
	BookController controller = BookController.getInstance();
	
	//메소드
	public void update(Scanner sc) {
		// 멤버필드 채우고
		String bookNo, bookName, bookAuthor, bookDate, pubNo;
		int bookPrice, bookStock;
		
		System.out.println("\n*************************");
		System.out.println("도서정보 수정");
		System.out.println("***************************");
		
		// 수정이므로 입력된 데이터 먼저 보여주고 수정 데이터입력 받기
		BookList bls = new BookList();
		bls.getAllBook();
		
		// 날아가는 데이터 가져 오기
		System.out.print("수정할 도서번호 입력 : ");		
		bookNo = sc.nextLine();
		
		System.out.print("도서제목 입력 : ");	
		bookName = sc.nextLine();
		
		System.out.print("도서저자 입력 : ");	
		bookAuthor = sc.nextLine();
		
		System.out.print("도서가격 입력 : ");	
		bookPrice = Integer.parseInt(sc.nextLine());
		
		System.out.print("발행일 입력 : ");	
		bookDate = sc.nextLine();
		
		System.out.print("도서재고 입력 : ");	
		bookStock = Integer.parseInt(sc.nextLine());
		
		System.out.print("출판사번호 입력 : ");	
		pubNo = sc.nextLine();	
		System.out.println("***************************");
		
		// 컨트롤러에 수정 데이터 전달
		controller.update(new BookDTO(bookNo, bookName, bookAuthor,bookPrice, bookDate,bookStock, pubNo));
	}
}
