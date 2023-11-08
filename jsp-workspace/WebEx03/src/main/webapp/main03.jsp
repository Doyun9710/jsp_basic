<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int i = 10;
%>

Hello1.jsp<br />
<!-- Hello2.jsp<br /> -->
<%@ include file="sub03.jspf" %>
Hello3.jsp<br /><br />

i = <%= i %><br />
</body>
</html>