package model;

import java.util.Vector;
	// CRUD
public interface IBookDAO {
	// 입력
	public boolean insert(BookDTO dto) throws Exception;	// 어제는 catch{e.prinrStack()}으로 일관함
	// 수정
	public boolean update(BookDTO dto) throws Exception;	// 오류 처리는 메소드를 호출한 쪽으로 전달
	// 삭제
	public boolean delete(BookDTO dto) throws Exception;	// 어제는 void로 잡았는데 오늘은 boolean으로 참/거짓을 받아냄
	// 조회
	public Vector<BookDTO> getAllBook() throws Exception;	//Vector vs ArrayList 내부처리만 다르지 비슷함
}
