<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Hello.jsp
</body>
</html>
<%
	// jsp()
	response.sendRedirect( "https://www.daum.net" );

	// jsvascript(O), 경고 기능 가능
	out.println( "<script type='text/javascript'>" );
	out.println( "location.href='./board_list1.jsp';" );
	out.println( "</script>" );
%>