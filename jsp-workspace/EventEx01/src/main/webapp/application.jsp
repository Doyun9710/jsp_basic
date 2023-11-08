<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- application.jsp -->
<%
	ServletContext context = getServletContext();

	context.setAttribute( "name", "홍길동" );
	out.println( "추가<br />" );
	
	context.setAttribute( "name", "김철수" );
	out.println( "수정<br />" );
	
	context.removeAttribute( "name" );
	out.println( "삭제<br />" );
%>
</body>
</html>