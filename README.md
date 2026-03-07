# JDBC
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

<br /><br />
<hr /><br />
# 📚 도서 관리 및 구매 시스템 (Book Management & Sales System)
> **Java와 Oracle DB를 연동한 MVC 패턴 기반의 도서 재고 관리 및 구매 프로그램**

사용자가 도서를 등록하고, 실시간으로 재고를 관리하며 구매 및 취소할 수 있는 CLI(Command Line Interface) 기반 시스템입니다. 재고 유효성 검사와 테이블 조인(Join) 등 실무적인 로직이 포함되어 있습니다.
2026.02.24
---

## 🛠 기술 스택 (Tech Stack)
- **언어 (Language)**: Java (JDK 17)
- **데이터베이스 (Database)**: Oracle Database Express Edition 21c
- **라이브러리 (Library)**: JDBC (ojdbc11.jar)
- **아키텍처 (Architecture)**: MVC Pattern (Model-View-Controller)

---

## ✨ 핵심 기능 (Key Features)

### 1. 도서 관리 (Book Management)
- 도서 정보의 **CRUD(등록, 조회, 수정, 삭제)** 기능 구현.
- 도서 번호, 제목, 저자, 가격, 발행일, 재고량 데이터 관리.

### 2. 구매 및 재고 시스템 (Sales & Stock)
- **도서 구매**: 고객번호와 도서번호를 연동하여 구매 내역 생성.
- **실시간 재고 연동**:
  - 구매 시: 도서 재고(`BOOKSTOCK`) 자동 차감.
  - 수정 시: 기존 구매 수량과 변경 수량의 차이만큼 재고 자동 조정.
  - 취소 시: 구매했던 수량만큼 도서 재고 자동 복구.
- **재고 유효성 검사**: 구매 전 현재 재고를 확인하여 **재고 부족 시 구매를 차단**하는 로직 구현.

### 3. 출판사별 조회 (Search by Publisher)
- `JOIN` 쿼리를 활용하여 특정 출판사가 출판한 도서 목록만 필터링하여 조회.
- `Vector<DTO>` 컬렉션을 사용한 데이터 핸들링.

### 4. 자동 번호 생성 (Sequence)
- Oracle `SEQUENCE`를 활용하여 주문번호(`BSNO`)를 중복 없이 자동 생성.

---

## 🏗 프로젝트 구조 (Project Structure)
```text
src
 ┣ controller
 ┃ ┣ BookController.java        # 도서 제어 (Singleton)
 ┃ ┗ BooksaleController.java    # 구매 및 재고 유효성 검사 제어
 ┣ dao
 ┃ ┣ IBookDAO.java              # 도서 인터페이스
 ┃ ┣ BookDAO.java               # 도서 DB 접근 로직
 ┃ ┣ IBooksaleDAO.java          # 구매 인터페이스
 ┃ ┗ BooksaleDAO.java           # 구매/재고 업데이트 SQL 실행
 ┣ model (DTO)
 ┃ ┣ BookDTO.java               # 도서 데이터 객체
 ┃ ┗ BooksaleDTO.java           # 구매 데이터 객체
 ┣ view (UI)
 ┃ ┣ MainView.java              # 메인 메뉴 화면
 ┃ ┣ BookView.java              # 도서 입출력 화면
 ┃ ┣ BooksaleView.java          # 구매/취소 화면
 ┃ ┗ ResultView.java            # 공통 결과 메시지 출력
 ┗ util
   ┗ DBConnect.java             # Oracle DB Connection 관리
```
## 📝 주요 SQL (Database Schema)
```sql
-- 도서 테이블
CREATE TABLE BOOK (
    BOOKNO VARCHAR2(10) PRIMARY KEY,
    BOOKNAME VARCHAR2(50) NOT NULL,
    BOOKAUTHOR VARCHAR2(30),
    BOOKPRICE NUMBER,
    BOOKDATE DATE,
    BOOKSTOCK NUMBER DEFAULT 0,
    PUBNO VARCHAR2(10)
);

-- 구매 테이블
CREATE TABLE BOOKSALE (
    BSNO NUMBER PRIMARY KEY,
    BSDATE DATE DEFAULT SYSDATE,
    BSQTY NUMBER NOT NULL,
    CLIENTNO VARCHAR2(10),
    BOOKNO VARCHAR2(10) REFERENCES BOOK(BOOKNO)
);

-- 구매 시퀀스
CREATE SEQUENCE seq_bs START WITH 1 INCREMENT BY 1 NOCACHE;
```

## 💡 프로젝트 후기 (Lessons Learned)
MVC 패턴의 체득: View, Controller, DAO, DTO의 역할을 명확히 구분하여 코드의 결합도를 낮추는 경험을 했습니다.
데이터 일관성 관리: 주문과 재고 수량 사이의 관계를 로직으로 풀어내며 데이터 무결성의 중요성을 깨달았습니다.
JDBC 예외 처리: SQLException 등 DB 연동 시 발생하는 다양한 예외 상황에 대응하며 견고한 코드를 작성하는 연습을 했습니다.

2026.03.05