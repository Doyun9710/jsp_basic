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

<form action="./gugudan02.jsp" method="post">
시작단 <input type="text" name="startdan" />&nbsp;
~
끝단 <input type="text" name="enddan" />
<input type="submit" value="구구단 보기" />
</form>

<br /><hr /><br />

<c:if test="${ !empty(param.startdan) && !empty(param.enddan) }">
	<table width="800" border="1" cellspacing="0">
		<c:forEach var="row" begin="${ param.startdan - 1 }" end="${ param.enddan }">
			<tr>
			<c:forEach var="col" begin="0" end="9">
				<td>
				<c:choose>
					<c:when test="${ row == (param.startdan - 1) && col == 0 }">&nbsp;</c:when>
					<c:when test="${ row == (param.startdan - 1) }">X ${ col }</c:when>
					<c:when test="${ col == 0 }">${ row } 단</c:when>
					<c:otherwise>${ row } X ${ col } = ${ row * col }</c:otherwise>
				</c:choose>
				</td>
			</c:forEach>
			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>