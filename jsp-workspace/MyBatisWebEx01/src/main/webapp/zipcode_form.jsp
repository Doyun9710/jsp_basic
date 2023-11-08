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
		document.getElementById( 'sbtn' ).onclick = function() {
			//alert( 'click' );
			document.sfrm.submit();
		}
	}
</script>
<body>

<form action="zipcode_ok.jsp" method="post" name="sfrm">
입력 : <input type="text" name="strdong" value="" />
<input type="button" id="sbtn" value="검색"  />
</form>

</body>
</html>