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
			document.frm.submit();
		};
	};
</script>
<body>

<form action="./dept_insert_ok.jsp" method="post" name="frm">
	부서번호 <input type="text" name="deptno" /><br />
	부서이름 <input type="text" name="dname" /><br />
	부서위치 <input type="text" name="loc" /><br />
	<input type="button" id="btn" value="입력" />
</form>

<br /><hr /><br />

</body>
</html>