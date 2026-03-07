package view;

public class ResultView {
	// 컨트롤러가 view(service) 요청 처리 후 사용자에게 보여줄 결과 페이지
	public static void succMsg(String msg) {
		System.out.println("성공: "+ msg);
	}
	public static void errorMsg(String msg) {
		System.out.println("실패: "+ msg);
	}
}