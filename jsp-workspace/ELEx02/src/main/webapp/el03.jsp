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
	pageContext.setAttribute( "name1", "홍길동" );
	// view
	request.setAttribute( "name2", "김철수" );
	// session
	session.setAttribute( "name3", "임꺽정" );
	application.setAttribute( "name4", "장길산" );
%>
<%= pageContext.getAttribute( "name1" ) %><br />
<%= request.getAttribute( "name2" ) %><br />
<%= session.getAttribute( "name3" ) %><br />
<%= application.getAttribute( "name4" ) %><br />
${ name1 }<br />
${ name2 }<br />
${ name3 }<br />
${ name4 }<br />

</body>
</html>