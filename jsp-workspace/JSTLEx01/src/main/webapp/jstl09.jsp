<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="hm1" value="<%= new java.util.HashMap() %>" />
<c:set target="${ hm1 }" property="name1" value="홍길동" />
<c:set target="${ hm1 }" property="name2" value="김철수" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="data" items="${ hm1 }">
	${ data.key } - ${ data.value }<br />
</c:forEach>

</body>
</html>