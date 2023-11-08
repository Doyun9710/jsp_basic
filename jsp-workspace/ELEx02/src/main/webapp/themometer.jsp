<%@page import="model1.Themometer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Themometer themometer = new Themometer();
	request.setAttribute( "t", themometer);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>온도 변환 예제</title>
</head>
<body>
	${ t.setCelsius('서울', 27.3) }
	서울 온도 : 섭씨 ${ t.getCelsius('서울') }도 / 화씨 ${ t.getFahrenheit('서울') }
	
	<br />
	정보 : ${ t.info }
</body>
</html>