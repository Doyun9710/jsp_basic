<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model1.BoardTO" %>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	BoardTO to = new BoardTO();
	to.setSubject( "제목" );
	to.setWriter( "작성자" );
	
	out.println( to.getSubject() + "<br />" );
	out.println( to.getWriter() + "<br />" );
%>

</body>
</html>