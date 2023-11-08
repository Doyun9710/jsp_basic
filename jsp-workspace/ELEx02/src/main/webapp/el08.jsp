<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>
<%@ page import="model1.BoardListTO" %>
<%@ page import="java.util.ArrayList" %>

<%
	BoardTO to1 = new BoardTO();
	to1.setSubject( "제목1" );
	to1.setWriter( "작성자1" );
	
	BoardTO to2 = new BoardTO();
	to2.setSubject( "제목2" );
	to2.setWriter( "작성자2" );
	
	BoardListTO listTO1 = new BoardListTO();
	listTO1.setCpage( "1" );
	listTO1.setBoardTO( to1 );
	
	BoardListTO listTO2 = new BoardListTO();
	listTO2.setCpage( "2" );
	listTO2.setBoardTO( to2 );
	
	ArrayList<BoardListTO> lists = new ArrayList();
	lists.add( listTO1 );
	lists.add( listTO2 );

	request.setAttribute( "lists", lists );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- to1 -> subject / to2 -> subject : EL -->
페이지 1 : ${ lists[0].cpage }<br />
페이지 2 : ${ lists[1].cpage }<br />

제목 1 : ${ lists[0].boardTO.subject }<br />
작성자 1 : ${ lists[0].boardTO.writer }<br />

</body>
</html>