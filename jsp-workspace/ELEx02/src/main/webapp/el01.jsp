<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	// scriptlet
	out.println( "2" ); 
%>
<br />
<!-- expression -->
<%= "2" %>
<br />
<!-- expression lang -->
${ "2" }<br />
${ "2" + 5 }<br />
${ "2" + "5" }<br />
${ "2" }${ "5" }<br />

${ "test" }<br />
\${ "test" + 2 }<br />

${ 2 < 3 }<br />
${ 2 lt 3 }<br />

${ empty data }<br />
</body>
</html>