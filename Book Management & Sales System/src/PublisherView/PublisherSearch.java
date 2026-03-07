package PublisherView;

import java.util.Scanner;
import java.util.Vector;

import controller.BookController;
import model.BookDTO;

public class PublisherSearch {
	BookController controller = BookController.getInstance();
	
	public void searchPub(Scanner sc) {
		System.out.print("조회할 출판사 번호 입력: ");
		String pubNo = sc.nextLine();

		controller.selectByPubNo(pubNo);
	}
	
	public static void printPublisherList(Vector<BookDTO> list) {
		System.out.println("\n*************************");
		System.out.println("도서번호\t도서명\t\t저자\t가격\t재고");
		System.out.println("***************************");
		for(BookDTO dto:list) {
            System.out.print(dto.getBookNo() + "\t");
            System.out.print(dto.getBookName() + "\t");
            System.out.print(dto.getBookAuthor() + "\t");
            System.out.print(dto.getBookPrice() + "\t");
            System.out.println(dto.getBookStock());
		}
		System.out.println("***************************");
	}
}