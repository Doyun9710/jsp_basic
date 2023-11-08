<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- session03.jsp -->
<%
	out.println( "세션 : " + session.getAttribute( "data1" ) );
%>
</body>
</html>