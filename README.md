


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
calendar, calendar_ok : 년도, 월 < select > -> 달력 테이블   

gugudan2 : gugudan1 + gugudan1_ok   
calendar04 : calendar03 + calendar03_ok   


<br><br>


< JDBCEx01 >   
jdbc01 : dept table 출력   

dept, dept_ok : dept.jsp(사용자 입력) -> dept_ok.jsp(데이터베이스 insert)   

dept_list : dept2 table 목록   
dept_insert : 입력항목   
dept_insert_ok : 데이터베이스 입력   
dept_delete_ok : 데이터베이스 삭제   


<br><br>


< JDBCEx02 >   
커넥션 풀   
C:\Java\jsp-workspace\JDBCEx01\src\main\webapp\META-INF\context.xml   

jdbc01 : DB 연결   
jdbc02 : 데이터 출력   


<br><br>


< JDBCEx03 >   
zipcode01 : select * from zipcode where dong like ?   
zipcode02 : zipcode01   


<br><br>


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


<br><br>


< UploadEx01 >   
upload : 파일 전송 <form>   
upload_ok : 파일 전송   


<br><br>


< BoardEx01 >   
(mariadb/pds1)   
board_write1 : enctype="multipart/form-data"   
board_write1_ok : request -> multi   
board_modify1_ok :    
![image](https://github.com/Doyun9710/jsp_basic/assets/116128876/d7be8797-3afc-47b2-8e54-5fbe056fc941)



(mariadb/album1)   
CRUD   

(mariadb/album2)   
페이지 넘버(+ 2줄)   

(mariadb/album3)   
no image   


<br><br>


< WebEx02 >   
out01 : buffer, autoFlush   
out02 : out method   
response01 : 페이지 이동 jsp 방식, javascript 방식   
pageContext01 : JspWriter, out = writer   
ex5_3 : JSP 120p 예제 5.3 기본 객체 접근 메서드   


<br><br>


< WebEx03 >   
application01 : application.getInitParameter   
application02 : application.getInitParameter   
ex5_5 : JSP 123p 예제 5.5 초기화 파라미터 읽어오기   
main, sub : < jsp:include >   

main02, header, footer : header, footer 외부파일 연동   

main03, sub03.jspf : include 디렉티브, jspf 파일은 에러 무시, <%@ include file=".jspf" %>   

main04, sub04 : JSP 177p ~ 181p, < jsp:forward >   


(forward)   
a, b, c : out.println( a, b, c )   
forward : <form>a, b, c   
forward_ok : RequestDispatcher   


<br><br>


< WebEx04 >   
../Java Resources/src/main/java/pack1/MemberTO : id, password getter/setter   
bean01 : java 형식   
bean02 : jsp:useBean   
bean03 : jsp:setProperty   
bean04 : Solution(package) 형식   

(hardcoding)   
Zipcode, Zipcode_ok : search dong -> table   

(model1)   
Zipcode, Zipcode_ok : search dong -> table   
ZipcodeDAO : searchZipcode()   
ZipcodeTO : zipcode, sido, gugun, dong, ri, bunji   



<br><br>


< BoardModel1Ex01 >   
Board1 --(model1)--> BoardModel1Ex01   


<br><br>


< EmotBoardModel1 >   


< PagingModelEx01 >   


< PDSModelEx01 >   


< AlbumModelEx01 >   


<br><br>


< WebEx05 >   
data01 : 기본 객체, pageContext.setAttribute()/getAttribute()   
request, request1_ok, request2_ok, request1_sub : pageContext 서로 다름, request 서로 같음   
session01 : JSP 235p, session 아이디, 유효시간, 생성시간/접속시간 date.toLocaleString()   
session02, session03 : session 값 공유   

(login1)   
login_from :    
login_ok :    
login_complete :    
logout_ok :    
   
(cookie1)   
setCookies, getCookies : 값, 시간 설정   

(cookie2)   
/util/Cookies : JSP 222p 예제 9.8   

(login2)   
login1_ok : cid, cgroup   


<br><br>


< MemberEx01 >   

(member1_d)   
login -> login_ok -> logout -> logout_out -> login   

(member2_d)   
+ 회원가입   
+ 정보수정   


<br><br>   


< ServletEx01 >   
(servlet1)   
ServletEx01 : sbHtml 출력   
ServletEx02 : get / post 방식 구별없이 요청을 처리   
ServletEx03 : get / post 방식 구별   
ServletEx04 : get / post 통합처리   
ServletEx05, ServletEx06 : web.xml <load-on-startup>실행순서</load-on-startup>   
JDBCServletEx01 : JDBC 기능 시험   


<br><br>


< GugudanEx01 >   
/gugudan → servlet1.GugudanServlet : 시작단, 끝단 입력   
/gugudan_ok → servlet1.GugudanOkServlet : 구구단 내용 출력   


<br><br>


< ServletEx02 >   
ServletEx01 : 어노테이션 Annotation   
ServletEx02 : new -> Servlet -> URL mapping ->    


<br><br>


< GugudanEx02 >   
< GugudanEx01 > + 어노테이션   


<br><br>


< ControllerEx01 >   
index, view1, view2 : parameter 방식   
ControllerEx01 :    
View1Action, View2Action :    


<br><br>


< ControllerEx02 >   
index, form, form_ok :    
ControllerEx01 :   
FormAction FormOkAction :    


<br><br>


< ControllerEx03 >   
< GugudanEx02 > + MVC   
controller?path=?gugudan → controller   
controller?path=?gugudan_ok → ControllerEx01.class   

path → gugudan   
	GugudanAction.class - execute   
	gugudan.jsp   
path → gugudan_ok   
	GugudanOkAction.class - execute   
	gugudan_ok.jsp   


<br><br>


< GugudanEx03 >   
< GugudanEx02 > + < ControllerEx03 >   


<br><br>


< GugudanEx04 >   
(model1/)Action : implements Action   


<br><br>


< ZipcodeModel2Ex01 >   
< WebEx04 >model1 -> model2   
![image](https://github.com/Doyun9710/jsp_basic/assets/116128876/a2772aa1-c1dd-44cf-b305-04e1358c09d3)



<br><br>


< BoardModel2Ex01 >   
< BoardModel1 > + model2 + (model2 folder move to WEB-INF)   
![image](https://github.com/Doyun9710/jsp_basic/assets/116128876/2727a5d3-4698-4130-bf54-1927bdb373ce)



model2   
board_write1.jsp   
board_write1_ok.jsp   
...   
board_delete1_ok.jsp   

css, image 파일 경로 ../../css/board.css -> ./css/board.css   


<br><br>


< EmotBoardModel2Ex01 >   
< EmotBoardModel1 > + model2   


<br><br>


< ZipcodeSearchModel2Ex02 >   
view1, view2 : .jsp   
(controller/)ControllerEx01 : Controller, path.equals("/view1.do")   


![image](https://github.com/Doyun9710/jsp_basic/assets/116128876/d9cfe7c2-042d-46af-a360-4b91dc091b70)



<br><br>


< BoardModel2Ex02 >   
![image](https://github.com/Doyun9710/jsp_basic/assets/116128876/cccc88f6-dcb1-46d3-a563-a72258dc508c)



<br><br>


< JavaEx01 > maven project   
App.java : JDBC 연결 확인   


<br><br>


< PDSModel2Ex02 > maven project   
설정 순서   
1. < PDSModel1Ex01 > -> (Dynamic Web project) + maven projcet(com.exam.model1)   
2. 	jdbc 드라이버   
>	multipart request   
3. 기타   
>	java, css, image, ...   
	
	
<br><br>


=================================   

AlbumCmt Project   
참고   

=================================   


<br><br>


< FilterEx01 >   
(com.exam.filter)FirstFilter : 필터 구현   

< FilterEx02 >   
(com.exam.filter)FirstFilter : < FilterEx01 > + 어노테이션(@WebFilter)   

< FilterEx03 >   
(com.exam.filter)EncodingFilter : request.setCharacterEncoding( "utf-8" ) 전처리   


<br><br>


< EventEx01 >   
(com.exam.event)FirstListener : contextDestroyed(), contextInitialized()   
(com.exam.event)SecondListener : 데이터 추가, 수정, 제거   
application : ServletContext context, context.setAttribute   
application2 : application.setAttribute, 로그(log)   


<br><br>


< EventEx02 >   
(com.exam.event)SessionAttributeListenerEx01 : attributeAdded(), attributeRemoved(), attributeReplaced()   
session : 데이터 추가, 수정, 삭제   



<br><br>


< ELEx01 >   
el01 : 표현언어, EL, 수치연산자, 비교연산자, ...   
el02 : pageContext.setAttribute   
el03 : request.setAttribute, session.setAttribute, application.setAttribute   
el04 : ${ name } 대상 우선순위   

(model1)BoardTO : subject, writer   
el05 : BoardTO private 변수 호출   
el06 : ArrayList   
el07 : HashMap   

(model1)BoardListTO : cpage, boardTO   
el08 : ArrayList<BoardListTO>   

header01 : header, user-agent   
ex11_1 : JSP 255p EL의 기본 객체   

(model1)Themometer, themometer : JSP 267p~268p 온도 변환 예제   


<br><br>


< JSTLEx01 >   
jstl01 : JSP 295p 코어 테그 라이브러리(추가참고 JSP 310p)   
jstl02 : JSP 296p 출력 <c:out>   
jstl03 : Scope 영역(위치) 확인   
jstl04 : 삭제 <c:remove>   

(model1)BoardTO : subject, writer   
jstl05 : 영역 지정 <c:set var="to" value="<%= new model1.BoardTO() %>" scope="page" />   
jstl06 : 조건문 <c:if test="">, 선택문 <c:choose test="">   
jstl07 : 반복문 <c:forEach var="i" begin="" end="">   
jstl08 : 향상 for문   
jstl09 : HashMap   
jstl10 : ArrayList   
jstl11 : jstl10 + only use EL   
jstl12 : <c:forTokens var="" items="" delims="">   
jstl13 : redirect   
jstl14 : <c:import url="" />   
jstl15 : https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do?serviceId=searchDailyBoxOffice   

ex12_1 : JSP 300p   
ex12_1 : JSP 301p   
ex12_1 : JSP 304p   

gugudan01 : 1~9단 출력   
gugudan02 : 입력단 출력   


<br><br>   


< JSTLEx02 >   
jstl01 : insert   
jstl02 : update   
jstl03 : select   
jstl04 : context.xml 이용, 풀링   
zipcode1 : select * from zipcode where dong like ?   
ex12_17 : JSP 333p JSTL 제공 EL 함   



<br><br>


< MailEx01 > maven project   
pom.xml : <dependencies> 추가   
MyAuthenticator : 계정 설정 환경   
App : 메일 text, html, img 보내기   


<br><br>


< MailWebEx01 > maven project   
pom.xml : <dependencies> 추가   
MyAuthenticator : 계정 설정 환경   
MailSender : 메일 text, html, img 보내기   



<br><br>


< LombokEx01 >   
(pack1)BoardTO : Source -> Generate Getter and Setter   
(pack2)BoardTO : @Getter/Setter   
(pack3)BoardTO : private @Getter @Setter String subject;   
(pack4)BoardTO : @AllArgsConstructor, 생성자   
(pack5)BoardTO : @RequiredArgsConstructor, Not Null   
(pack6)   
DeptTO : Annotation 없이 생성   
DeptTO2 : @RequiredArgsConstructor(staticName = "of" )   
(pack7)DeptTO : @ToString, @EqualsAndHashCode   


<br><br>


< LombokWebEx01 >   
(model1)BoardTO :   
hello :    


<br><br>


< MyBatisEx01 >   
log4j.xml : 출력 로그 설정   
dept.xml : sql문 미리 선언 및 id 설정   
maBatisConfig.xml : JDBC 설정   

MyBatisEx01 : xml 설정 호출   
MyBatisEx02 : sqlSession = sqlSessionFactory.openSession();   
MyBatisEx03 : select 결과 Map 형식으로 받기/출력   
MyBatisEx04 : List<Map<String, String>> 형식 출력   
MyBatisEx05 : model1, Getter/Setter 기존 방식   
MyBatisEx06 : model1, Annotation 방식   


<br><br>


< MyBatisEx02 >   
myBatisConfig -> zipcode.xml -> ZipcodeTO -> MyBatisEx01   
MyBatisEx01 : 단순 select   
MyBatisEx02 : zipcode.xml parameterType="String" 이용, 검색 값 하나   
MyBatisEx03 : 검색 값 두개   
MyBatisEx04 : select where like ?   


<br><br>


< MyBatisWebEx01 >   
zipcode_form, zipcode_ok : select where dong like strdong   

dept_insert : insert   
dept_update : update   


<br><br>


< MyBatisWebEx02 > maven project   
dependencies   
>	mariadb   
>	log4j   
>	mybatis   
>	projectlombok   
DeptDAO : selectlist()   


<br><br>


< MyBatisWebEx03 > maven project   
ZipcodeDAO : selectlist(String strdong)   


<br><br>


< MyBatisWebEx04 > maven project   
sql -> xml 사용 X, 어노테이션 사용   
dept1 : DAO 이용 select   
dept2 : 
>	mapper 이용 select   
>	환경설정 순서   
>	myBatisConfig.xml mapper tag 삭제 ->    
>	com.exam.mapper package 생성 ->    
>	SqlMapperInter.java selectByDeptno() 생성   
dept3 : dept select   
insert, update, delete : CRUD   

zipcode_select, zipcode_select_ok : 우편번호 검색   


<br><br>


< MyBatisBoardEx01 >   
Album model1 + MyBatis + sql.xml + list 검색 기능   


< MyBatisBoardEx02 >   
Album model1 + MyBatis + Mapper.java(Annotation) + list 검색 기능   


< MyBatisBoardEx022 >   
Album_simple model1 + MyBatis + Mapper.java + list 검색 기능   


< MyBatisBoardEx0222 >   
Album_simple model1 + MyBatis + Mapper.java(Annotation) + list 검색 기능   


<br><br>


< MyBatisEx03 >   
DDL : create table, drop table   
MyBatisEx01 : create table   
MyBatisEx02 : drop table   
MyBatisEx03 : namespace, refid 설정   




