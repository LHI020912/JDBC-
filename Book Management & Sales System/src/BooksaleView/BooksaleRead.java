package BooksaleView;

import java.util.Scanner;

import controller.BooksaleController;
import model.BooksaleDTO;

public class BooksaleRead {
	BooksaleController controller = BooksaleController.getinstance();
	
	public void read(Scanner sc) {
		System.out.print("조회할 주문번호 입력: ");
		String clientNo = sc.nextLine();
		
		controller.read(clientNo);
	}

	public static void printBooksaleOne(BooksaleDTO dto) {
		System.out.println("\n*************************");
		System.out.println("주문번호\t주문일자\t\t\t수량\t고객번호\t도서번호");
		System.out.println("***************************");
		System.out.print(dto.getBsNo() + "\t");
        System.out.print(dto.getBsDate() + "\t");
        System.out.print(dto.getBsQty() + "\t");
        System.out.print(dto.getClientNo() + "\t");
        System.out.println(dto.getBookNo());
		System.out.println("***************************");
	}
}
