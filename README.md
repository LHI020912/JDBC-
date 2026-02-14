# JDBC-
이클립스에서 JDBC를 이용해 연동하는 실습 진행<br />

## 주요 구성 요소
<p><b> DriverManager </b> : JDBC 드라이버를 로드하고 관리하며, 데이터베이스와의 연결(Connection)을 생성</p>
<p><b> Connection </b> : 물리적인 연결 상태를 나타내는 인터페이스</p>
<p><b> Statement </b> : SQL 문을 데이터베이스에 전송하고 실행하는 그릇</p>
<p><b> ResultSet </b> : SELECT문 실행 결과(데이터)를 저장하는 테이블 형식의 객체</p>

<br />

### DB연결과 예외처리
```
Connection con = null;
try { 
    String url = "jdbc:종류:@ip주소:포트:sid:";
    String user ="USER";
    String pwd = "0000";

    con =DriverManager.getConnection(url,user,pwd);

}catch(Exception e){
  e.printStackTrace();
}
```
- 외부 데이터베이스 연결은 예외가 발생할 수 있으므로 try-catch 블록을 사용하여 예외를 처리

<br />

### ex) 등록된 값 확인
```
// 등록된 값 확인: select 절
			sql = "select * from book where bookNo=?";
			PreparedStatement pstmtSel = con.prepareStatement(sql);
			pstmtSel.setString(1, bookNo);
			rs = pstmtSel.executeQuery(); // 바인딩 인자 주의
```
<br />

``` close() ``` 객체를 사용한 후에는 반드시 close() 메소드를 호출하여 자원을 해제하기.
