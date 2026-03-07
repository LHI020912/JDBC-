package BooksaleView;

import java.util.Scanner;


public class BooksaleMainView {

		public static void menu(Scanner sc) {
			
		int m;
		
		while(true) {
			System.out.println("******* 도서 구매 프로그램 *******");
			System.out.println("******* 다음 메뉴에서 선택 *******");
			System.out.println("1. 도서 구매");
			System.out.println("2. 구매 내역 조회");
			System.out.println("3. 구매 내역 수정");
			System.out.println("4. 구매 취소");
			System.out.println("5. 이전 메뉴");
			System.out.println("-----------------------------");
			System.out.print("메뉴 번호 입력: ");
			m = Integer.parseInt(sc.nextLine());
			
			switch(m) {
			case 1: BooksaleCreate bc = new BooksaleCreate();
					bc.insert(sc);break;
					
			case 2: BooksaleRead bsr = new BooksaleRead();
					bsr.read(sc); break;
					
			case 3: BooksaleUpdate bsu = new BooksaleUpdate();
					bsu.update(sc); break;
					
			case 4: BooksaleDelete bsd = new BooksaleDelete();
					bsd.delete(sc); break;
					
			case 5: return;
			
			default: System.out.println("숫자를 잘못 입력하셨습니다.");
			}
		}
	}
}
