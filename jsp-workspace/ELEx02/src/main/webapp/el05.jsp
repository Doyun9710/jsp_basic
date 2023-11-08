<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>

<%
	/// XXXAction
	BoardTO to = new BoardTO();
	to.setSubject( "제목" );
	to.setWriter( "작성자" );
	
	// 가능
	//pageContext.setAttribute( "to", to );
	request.setAttribute( "to", to );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${ to.subject }<br />
${ to.writer }<br />

</body>
</html>