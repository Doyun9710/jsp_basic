<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	BoardTO to1 = new BoardTO();
	to1.setSubject( "제목1" );
	to1.setWriter( "작성자1" );
	
	BoardTO to2 = new BoardTO();
	to2.setSubject( "제목2" );
	to2.setWriter( "작성자2" );
	
	ArrayList<BoardTO> lists = new ArrayList();
	lists.add(to1);
	lists.add(to2);
	
	request.setAttribute( "lists", lists );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- EL / JSTL -->
<c:forEach var="i" begin="0" end="<%= lists.size()-1 %>">
	${ lists[i].subject }<br />
	${ lists[i].writer }<br />
</c:forEach>

<hr />

<c:forEach var="to" items="${ lists }">
	${ to.subject }<br />
	${ to.writer }<br />
</c:forEach>

</body>
</html>