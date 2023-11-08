<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding( "utf-8" );
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="zipcode1.jsp" method="post">
동이름 : <input type="text" name="dong" />
<input type="submit" value="우편번호 검색" />
</form>

<br /><hr /><br />

결과 출력<br />
<c:if test="${ !empty(param.dong) }">
	<sql:setDataSource 
		var="ds" 
		url="jdbc:mariadb://localhost:3306/project"
		driver="org.mariadb.jdbc.Driver"
		user="root"
		password="!123456" 
		scope="page" />
	
	<sql:query
		var="rs"
		dataSource="${ ds }" >
		select zipcode, sido, gugun, dong, ri, bunji, seq from zipcode where dong like ?
		<sql:param value="${ param.dong }%" />
	</sql:query>

	<table width="800" border="1" cellspacing="0">
	<tr>
		<c:forEach var="columnname" items="${ rs.columnNames }">
			<td>${ columnname }</td>
		</c:forEach>
	</tr>
	
		<c:forEach var="row" items="${ rs.rows }">
			<tr>
				<td>${ row.zipcode }</td>
				<td>${ row.sido }</td>
				<td>${ row.gugun }</td>
				<td>${ row.dong }</td>
				<td>${ row.ri }</td>
				<td>${ row.bunji }</td>
				<td>${ row.seq }</td>
			</tr>
		</c:forEach>
	
	</table>
</c:if>

</body>
</html>