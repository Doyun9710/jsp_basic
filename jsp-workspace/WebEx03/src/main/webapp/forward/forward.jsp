<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- forword.jsp -->
<form action="forward_ok.jsp" method="post">
url : 
<select name="url">
	<option value="a.jsp">a.jsp</option>
	<option value="b.jsp">b.jsp</option>
	<option value="c.jsp">c.jsp</option>
</select>
<input type="submit" value="페이지 보기" />
</form>
</body>
</html>