# jsp_basic



&#160;
< WebEx01 >
ex01 : import Date
ex02 : 월 지정 달력
calendar01 : 현재 달력
calendar02 : calendar01 + 문법 개선

ex03 : 메서드 선언, <%!

Ex3_13 : 73p, 예제 3.13 request 기본 객체의 클라이언트 및 서버 정보 관련 메서드

form, form_ok : GET, POST, 데이터 전송, POST 다국어 처리

gugudan1, gugudan1_ok : POST, 구구단 출력

Ex3_14, Ex3_15 : 76p~78p, 예제 3.14, 3.15 request 기본 객체의 요청 파라미터 관련 메서드

calendar03, calendar03_ok : 년도, 월 입력 -> 달력 테이블
calendar, calendar_ok : 년도, 월 <select> -> 달력 테이블

gugudan2 : gugudan1 + gugudan1_ok
calendar04 : calendar03 + calendar03_ok

&#160;
< WebEx02 >
ex01 : Hello JSP
/dir1/ex01 : Hello JSP

js01 : 필수 입력 항목 검사(data == null)


< JDBCEx01 >
jdbc01 : dept table 출력

dept, dept_ok : dept.jsp(사용자 입력) -> dept_ok.jsp(데이터베이스 insert)

dept_list : dept2 table 목록
dept_insert : 입력항목
dept_insert_ok : 데이터베이스 입력
dept_delete_ok : 데이터베이스 삭제


< JDBCEx02 >
커넥션 풀
C:\Java\jsp-workspace\JDBCEx01\src\main\webapp\META-INF\context.xml

jdbc01 : DB 연결
jdbc02 : 데이터 출력


< JDBCEx03 >
zipcode01 : select * from zipcode where dong like ?
zipcode02 : zipcode01


< BoardEx01 >
(mariadb/simple1)
board_write1, board_write1_ok : POST 데이터 확인 -> insert subject, writer, mail, password, content, wip
board_list1 : select datediff
board_view1 : select ~ where seq=?, 조회수 증가
board_modify1, board_modify1_ok : 
board_delete1, board_delete1_ok : input type="hidden"


(mariadb/emoticon1)
board_modify1 : 깔끔
board_modify2 : 삼항연산자 이용 이모티콘 체크(노가다)


(mariadb/simple2)
board_insert1_ok : 데이터 100개 생성/입력
board_list1 : + 페이지넘버

(mariadb/paging1)
simple2 + cpage 적용

(mariadb/paging_emoticon1)
-> emoticon1 + simple2
insert_ok
list
write
view
modify modify_ok
delete delete_ok


(mariadb/reply1)
board_reply1 : (new) 답글쓰기
board_reply1_ok : SELECT grp, grps, grpl -> UPDATE grps -> INSERT 


< UploadEx01 >
upload : 파일 전송 <form>
upload_ok : 파일 전송


< BoardEx01 >
(mariadb/pds1)
board_write1 : enctype="multipart/form-data"
board_write1_ok : request -> multi
board_modify1_ok : 
	새로운 파일이 있는 경우
		update pds_board1 set subject=?, mail=?, content=?, filename=?, filesize=? where seq=? and password=?
		기존 파일이 있는 경우
			기존 파일 삭제
		기존 파일이 없는 경우 X
	새로운 파일이 없는 경우
		update pds_board1 set subject=?, mail=?, content=? where seq=? and password=?
		기존 파일이 있는 경우 X
		기존 파일이 없는 경우 X


(mariadb/album1)
CRUD

(mariadb/album2)
페이지 넘버(+ 2줄)

(mariadb/album3)
no image


< WebEx03 >
out01 : buffer, autoFlush
out02 : out method
response01 : 페이지 이동 jsp 방식, javascript 방식
pageContext01 : JspWriter, out = writer
ex5_3 : JSP 120p 예제 5.3 기본 객체 접근 메서드


< WebEx04 >
application01 : 
application02 : 
ex5_5 : JSP 123p 예제 5.5 초기화 파라미터 읽어오기












