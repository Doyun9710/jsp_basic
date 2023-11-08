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
			if( document.frm.year.value.trim() == '' ) {
				alert( '년도 입력' );
				return;
			} else {
				let year = parseInt(document.frm.year.value.trim());
				if( year <= 2021 || year >= 2024 ) {
					alert( '년도 재입력' );
					return;
				}
			}

			if( document.frm.month.value.trim() == '' ) {
				alert( '월 입력' );
				return;
			} else {
				let month = parseInt(document.frm.month.value.trim());
				if( month <= 0 || month >= 13 ) {
					alert( '월 재입력' );
					return;
				}
			}
			document.frm.submit();
		};
	};
</script>
<body>

<form action="./calendar_ok.jsp" method="post" name="frm">
년도 : <input type="text" name="year"><br />
월 : <input type="text" name="month"><br />
<input type="button" id="btn" value="달력보기" />
</form>

</body>
</html>