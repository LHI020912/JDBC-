package controller;
//2번 정보지시

import model.BookDTO;
import java.util.Vector;
import model.BookDAO;
import model.IBookDAO;
import view.BookList;
import view.ResultView;

public class BookController {
	// 싱글톤 : 단 하나의 객체(인스턴스)만 생성
	// static이지만 private이므로 class 내부에서만 사용 가능
	private static BookController instance = new BookController(); // 따로 관리됨
	IBookDAO dao = new BookDAO();
	
	// new 연산자 활용 객체 생성 불가능하도록 생성자 오버로딩
	private BookController () {}
	
	// 외부에서 객체인스턴스 공통 사용 가능하도록 public 메소드 추가
	public static BookController getInstance() {
		return instance;
	}
	
	//////////// 자 여기까지 컨트롤인거다!!!!! /////////////

	// View로부터 dto 전달받아 dao 통해 db에 저장한 후 결과에 따라 메시지 출력을 위한 view 호출
	// dao에서 던져지는 예외를 반드시 처리해야 함(일반적으로 예외는 컨트롤러가 처리)
	// date 처리나 가공해야 할 일이 생기면 controller에서 처리하는 거임
	public boolean insert(BookDTO newDto) { // 객체 인스턴스가 있어야 사용 가능 메소드
		try {
			// view에서 전달받은 데이터를 dao로 전달하기 전에 필요 시 가공 처리
			BookDTO dto = new BookDTO();
			
			dto.setBookNo(dto.getBookNo()); // 패턴
			dto.setBookName(newDto.getBookName());
			dto.setBookAuthor(newDto.getBookAuthor());
			dto.setBookPrice(newDto.getBookPrice());
			dto.setBookDate(newDto.getBookDate());
			dto.setBookStock(newDto.getBookStock());
			dto.setPubNo(newDto.getPubNo());
			
			if(dao.insert(dto))
				ResultView.succMsg("도서정보가 등록 되었습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("도서정보 등록 오류");
		}
		return true;
	}
	
	// 조회
	public void getAllBook() { // throws를 통해 예외 처리는 컨트롤러에서 하겟다함.
		try{
			Vector<BookDTO> dataSet = dao.getAllBook();
			
			if(dataSet.size() != 0) 
				BookList.showAllBook(dataSet);
			else
				ResultView.errorMsg("검색결과가 없습니다." ); // 테이블이 비어있음(오류가아닌 비어있단 메시지)
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("잠시 후에 재요청 바랍니다."); // 이번건 오류
		}
	}
	
	// 수정
	public void update(BookDTO newDto) {
		try {
			BookDTO dto = new BookDTO();
			
			dto.setBookNo(dto.getBookNo());
			dto.setBookName(newDto.getBookName());
			dto.setBookAuthor(newDto.getBookAuthor());
			dto.setBookPrice(newDto.getBookPrice());
			dto.setBookDate(newDto.getBookDate());
			dto.setBookStock(newDto.getBookStock());
			dto.setPubNo(newDto.getPubNo());
			
			if(dao.update(dto))
				ResultView.succMsg("도서정보가 수정 되었습니다.");
		}catch(Exception e) {
			ResultView.errorMsg("도서정보 수정 오류");
			e.printStackTrace();
		}
	}

	// 삭제
	public void delete(String bookNo) {
		try {
			BookDTO dto = new BookDTO();
			dto.setBookNo(dto.getBookNo());
			
			if(dao.delete(dto))
				ResultView.succMsg(bookNo + " 도서가 삭제 되었습니다.");
			else
				ResultView.errorMsg(bookNo + " 도서 정보는 없습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("잠시 후에 다시 요청해주세요.");
		}
	}
}
