<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<sql:setDataSource 
	var="ds" 
	url="jdbc:mariadb://localhost:3306/sample"
	driver="org.mariadb.jdbc.Driver"
	user="root"
	password="!123456" 
	scope="page" />

출력 : ${ ds }

<br /><hr /><br />

<%-- 
insert, update, delete
각종 ddl
--%>

<%-- 
<sql:update var="result" dataSource="${ ds }" sql="insert into dept2 values ( 10, '개발부', '서울' )" />
--%>

<sql:update
	var="result" 
	dataSource="${ ds }"
	sql="insert into dept2 values ( ?, ?, ? )" >
	<sql:param value="40" />
	<sql:param value="구매부" />
	<sql:param value="대구" />
</sql:update>

결과 : ${ result }