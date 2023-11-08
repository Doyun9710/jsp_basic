<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- application2.jsp -->
<%
	// Listener를 통한 값 추적 => 로그(log)
	
	//ServletContext context = getServletContext();
	application.setAttribute( "name", "홍길동" );
	out.println( "추가<br />" );
	
	application.setAttribute( "name", "김철수" );
	out.println( "수정<br />" );
	
	application.removeAttribute( "name" );
	out.println( "삭제<br />" );
%>
</body>
</html>