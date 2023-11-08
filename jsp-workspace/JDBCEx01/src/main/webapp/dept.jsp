<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById( 'btn' ).onclick = function() {
			// 검사
			
			document.frm.submit();
		};
		
		document.getElementById( 'btn2' ).onclick = function() {
			// 검사
			
			document.frm2.submit();
		};
	};
</script>
<body>

<!-- dept.jsp(사용자 입력) -> dept_ok.jsp(데이터베이스 입력) -->
<form action="./dept_ok.jsp" method="post" name="frm">
	부서번호 <input type="text" name="deptno" /><br />
	부서이름 <input type="text" name="dname" /><br />
	부서위치 <input type="text" name="loc" /><br />
	<input type="button" id="btn" value="입력" />
</form>

<br /><hr /><br />

<!-- 부서번호 중심으로 부서이름, 부서위치 수정 -->
<form action="./dept_update_ok.jsp" method="post" name="frm2">
	부서번호 <input type="text" name="deptno" /><br /><br />
	
	부서이름 <input type="text" name="dname" /><br />
	부서위치 <input type="text" name="loc" /><br />
	<input type="button" id="btn2" value="수정" />
</form>

</body>
</html>