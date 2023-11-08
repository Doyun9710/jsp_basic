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
출력 : <%= "browser1" %><br />
출력 : ${ "browser2" }<br />
출력 : <c:out value="browser3" /><br /><br />
<%
	pageContext.setAttribute( "data1", "browser4" );
%>
출력 : <%= pageContext.getAttribute( "data1" ) %><br />
출력 : ${ data1 }<br />
출력 : <c:out value="${ data1 }" /><br /><br />

출력 : <c:out value="${ data2 }" /><br />
출력 : <c:out value="${ data2 }" default="browser" /><br />
</body>
</html>