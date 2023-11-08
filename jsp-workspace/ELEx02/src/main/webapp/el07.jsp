<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>
<%@ page import="java.util.HashMap" %>

<%
	BoardTO to1 = new BoardTO();
	to1.setSubject( "제목1" );
	to1.setWriter( "작성자1" );
	
	BoardTO to2 = new BoardTO();
	to2.setSubject( "제목2" );
	to2.setWriter( "작성자2" );
	
	//BoardTO[] lists = { to1, to2 };
	
	HashMap<String, BoardTO> lists = new HashMap();
	lists.put( "to1", to1 );
	lists.put( "to2", to2 );

	request.setAttribute( "lists", lists );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

${ lists.to1.subject }<br />
${ lists.to2.subject }<br />

</body>
</html>