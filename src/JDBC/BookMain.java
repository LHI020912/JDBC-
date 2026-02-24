package sec01;

import java.sql.Connection;
import java.util.Scanner;

public class BookMain {

	public static void main(String[] args) {
		// 메뉴 1)총권수 2)년도이후검색 3)입력받은 문자 포함도서 4)가격이상 5)종료
		Scanner sc = new Scanner(System.in);
		
		int m;
		
		try (Connection con = new DBConnect().getConnection()){
			
			while(true) {
				System.out.println("------- 메뉴 ---------");
				System.out.println("1. 총 권수");
				System.out.println("2. 년도 검색");
				System.out.println("3. 저자 도서 검색");
				System.out.println("4. 가격 이상 검색");
				System.out.println("5. 종료");
				m = Integer.parseInt(sc.nextLine());
				
				if(m == 5) break;
				
				switch(m) {
				case 1: Book.total(con);	break;
				case 2: Book.date(con);		break;
				case 3: Book.author(con);	break;
				case 4: Book.price(con);	break;
				default:System.out.println("숫자를 잘 못 입력하였습니다.");
				}
			}
			System.out.println("종료합니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}