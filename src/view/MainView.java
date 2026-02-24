package view;

import java.util.Scanner;
import ClientView.ClientLogin;

public class MainView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m;
		
		ClientLogin login = new ClientLogin();
		
		if(login.login(sc)){
			ResultView.succMsg("로그인 성공!");
			while(true) {
				System.out.println("******* 도서 관리 프로그램 *******");
				System.out.println("******* 다음 메뉴에서 선택 *******");
				System.out.println("1. 도서 등록");
				System.out.println("2. 도서 정보 조회");
				System.out.println("3. 도서 정보 수정");
				System.out.println("4. 도서 정보 삭제");
				System.out.println("5. 종료");
				System.out.println("-----------------------------");
				System.out.print("메뉴 번호 입력: ");
				m = Integer.parseInt(sc.nextLine());
				
				switch(m) {
				case 1: BookInsert bis = new BookInsert();
						bis.insert(sc);break;
						
				case 2: BookList bls = new BookList();
						bls.getAllBook(); break;
						
				case 3: BookUpdate bdu = new BookUpdate();
						bdu.update(sc); break;
						
				case 4: BookDelete bdv = new BookDelete();
						bdv.delete(sc); break;
						
				case 5: 
					System.out.println("-------------------------");
					System.out.println("종료합니다");
					System.out.println("-------------------------");
					
					sc.close();
					System.exit(0);
					
				default: System.out.println("숫자를 잘못 입력하셨습니다.");
				}
			}
		}else {
			ResultView.errorMsg("로그인 실패");
		}
	}
}