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

<table border='1'>
	<c:forEach var="dan" begin="0" end="9">
		<tr>
		<c:forEach var="i" begin="0" end="9">
			<td>
			<c:choose>
				<c:when test="${ dan == 0 && i == 0 }">&nbsp;</c:when>
				<c:when test="${ dan == 0 }">X ${ i }</c:when>
				<c:when test="${ i == 0 }">${ dan } 단</c:when>
				<c:otherwise>${ dan } X ${ i } = ${ dan * i }</c:otherwise>
			</c:choose>
			</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table>

</body>
</html>