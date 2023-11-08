<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello JSP<br />
<%
	out.println( "Hello JSP<br />" );
	
	Date date = new Date();
	out.println( date.toString() );
%>
</body>
</html>