<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./calendar03_ok.jsp" method="post">
년도 : <input type="text" name="year" size="10"><br>
월 : <input type="text" name="month" size="10"><br>
<br>
<input type="submit" value="검색">
</form>

</body>
</html>