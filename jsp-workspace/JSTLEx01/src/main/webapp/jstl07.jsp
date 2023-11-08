<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="i" begin="1" end="10" step="2">
	Hello JSTL : ${ i }<br />
</c:forEach>

<br />

<c:forEach var="i" begin="1" end="3">
	<c:forEach var="j" begin="1" end="3">
		${ i } - ${ j }<br />
	</c:forEach>
</c:forEach>

</body>
</html>