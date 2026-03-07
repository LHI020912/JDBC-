package view;

import java.util.Scanner;
import controller.BookController;
import model.BookDTO;

public class BookInsert {
	// DB에 추가할 정보를 입력받아서 정보를 CONTROLLER에게 전달
	//BookController controller = new BookController(); // private으로 만들어져서 new연산자 불가
	BookController controller = BookController.getInstance();
	
	// Scanner: 표준입력장치를 사용하기 때문에 닫으면 객체를 다시 생성해야 함
	// 공통을로 한 번 열어서 모두 사용하고 마지막에 닫는게 좋음
	public void insert(Scanner sc) {
		String bookNo, bookName, bookAuthor, bookDate, pubNo;
		int bookPrice, bookStock;
		
		System.out.println("\n*************************");
		System.out.println("도서정보 등록");
		System.out.println("***************************");
		
		System.out.print("도서번호 입력 : ");		
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
		
		// 입력받은 데이터를 컨트롤러에게 전달
		controller.insert(new BookDTO(bookNo, bookName, bookAuthor,bookPrice, bookDate,bookStock, pubNo));
	}
}
