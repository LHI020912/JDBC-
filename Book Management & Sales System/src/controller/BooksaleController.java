package controller;
//2번 정보지시

import BooksaleView.BooksaleRead;
import model.BookDAO;
import model.BooksaleDAO;
import model.BooksaleDTO;
import view.ResultView;

public class BooksaleController {
	private static BooksaleController instance = new BooksaleController();
	BooksaleDAO dao = new BooksaleDAO();
	
	private BooksaleController() {}
	public static BooksaleController getinstance() {
		return instance;
	}
	
	// 구매
	public boolean insert(BooksaleDTO dto) {
		boolean result =false;
		try {
			// 유효성 검사: 현재 재고량 가져오기
			int currentStock = BookDAO.getBookStock(dto.getBookNo());
			// 유효성 검사: 재고가 구매 수량보다 적은지 체크
			if(currentStock<dto.getBsQty()) {
				ResultView.errorMsg("재고가 부족합니다. (현재 재고: "+ currentStock+"권");
				return false; // 구매 중단
			}
			
			// 유효성 검사: 수량이 0이나 음수인지 체크
			if(dto.getBsQty() <= 0) {
				ResultView.errorMsg("구매 수량은 1권 이상이어야 합니다.");
				return false; // 구매 중단
			}
			if(dao.create(dto)&& dao.stock(dto)) {
				ResultView.succMsg("도서 구매 및 재고 수정이 완료되었습니다.");
				result = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("도서 구매 시스템 오류");
		}
		return result;
	}
	
	// 조회
	public void read(String bsNo) {
		try {
			BooksaleDTO dto = new BooksaleDTO();
			dto.setBsNo(bsNo);
			
			if(dao.read(dto)) 
				BooksaleRead.printBooksaleOne(dto);
			else
				ResultView.errorMsg("해당 주문번호가 존재하지 않습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 수정
	public void update(BooksaleDTO dto) {
		try {
			BooksaleDTO oldDto = new BooksaleDTO();
			oldDto.setBsNo(dto.getBsNo());
			
			if(dao.read(oldDto)) {
				int oldQty = oldDto.getBsQty();
				int newQty = dto.getBsQty();
				int diffQty = newQty - oldQty;
				// 구매내역 수정
				if(dao.update(dto)) {
					// 재고 수정
					if(dao.stockUpdate(oldDto.getBookNo(), diffQty)) {
						ResultView.succMsg("구매 내역이 성공적으로 수정되었습니다.");
					}
				}
			}else
				ResultView.errorMsg("주문번호를 확인해주세요.");
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("수정 중 시스템 오류가 발생했습니다.");
		}
	}
	
	// 삭제
	public void delete(String bsNo) {
		try {
			BooksaleDTO dto = new BooksaleDTO();
			dto.setBsNo(bsNo);
			
			if(dao.read(dto)) { // 기존에 만든 상세조회 활용
				String bookNo = dto.getBookNo();
				int qty = dto.getBsQty();
				
				// 재고 복구
				if(dao.stockRestore(bookNo, qty)) {
					// 구매 내역 삭제
					if(dao.delete(bsNo))
						ResultView.succMsg("주문이 취소되었으며, 재고 "+qty+"권이 복구되었습니다.");
				}
			}else
				ResultView.errorMsg("존재하지 않는 주문번호입니다.");
		}catch(Exception e) {
			e.printStackTrace();
			ResultView.errorMsg("주문 취소 중 오류가 발생했습니다.");
		}
	}
	
}
