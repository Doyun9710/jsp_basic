<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./calendar_ok.jsp" method="post";>
년도 
<select name="year">
	<option value="2022">2022</option>
	<option value="2023">2023</option>
	<option value="2024">2024</option>
</select>
월
<select name="month">
	<option value="1">1월</option>
	<option value="2">2월</option>
	<option value="3">3월</option>
	<option value="4">4월</option>
	<option value="5">5월</option>
	<option value="6">6월</option>
	<option value="7">7월</option>
	<option value="8">8월</option>
	<option value="9">9월</option>
	<option value="10">10월</option>
	<option value="11">11월</option>
	<option value="12">12월</option>
</select>

<input type="submit" value="달력보기">
</form>

</body>
</html>