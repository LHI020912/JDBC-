package BooksaleView;

import java.util.Scanner;

import controller.BooksaleController;
import model.BooksaleDTO;

public class BooksaleUpdate {
	BooksaleController controller =BooksaleController.getinstance();
	
	public void update(Scanner sc) {
		System.out.println("\n*************************");
		System.out.print("수정할 주문번호 입력: ");
		String bsNo = sc.nextLine();
		System.out.print("변경할 수량 입력: ");
		int bsQty = Integer.parseInt(sc.nextLine());
		
		BooksaleDTO dto = new BooksaleDTO();
		dto.setBsNo(bsNo);
		dto.setBsQty(bsQty);
		
		controller.update(dto);
	}
}
