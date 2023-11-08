<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
<sql:setDataSource 
	var="ds" 
	url="jdbc:mariadb://localhost:3306/sample"
	driver="org.mariadb.jdbc.Driver"
	user="root"
	password="!123456" 
	scope="page" />
--%>

<sql:setDataSource 
	var="ds" 
	dataSource="jdbc/mariadb1" 
	scope="page" />

<sql:query
	var="rs"
	dataSource="${ ds }" 
	sql="select deptno, dname, loc from dept2" />

<table border="1">
<tr>
	<c:forEach var="columnname" items="${ rs.columnNames }">
		<td>${ columnname }</td>
	</c:forEach>
</tr>
<c:forEach var="row" items="${ rs.rows }">
<tr>
	<td>${ row.deptno }</td>
	<td>${ row.dname }</td>
	<td>${ row.loc }</td>
</tr>
</c:forEach>

<!-- 버전 에러 --> <%-- 
<c:forEach var="row" items="${ rs.rows }">
<tr>
	<td>${ row['부서번호'] }</td>
	<td>${ row['부서이름'] }</td>
	<td>${ row['부서위치'] }</td>
</tr>
</c:forEach>
--%>

</table>
