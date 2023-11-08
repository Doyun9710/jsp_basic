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

<c:forTokens var="token" items="1, 2, 3, 4, 5" delims=",">
	${ token }<br />
</c:forTokens>

<hr />

<c:set var="tokens" value="홍길동, 김길동, 고길동" />
<c:forTokens var="token" items="${ tokens }" delims=",">
	${ token }<br />
</c:forTokens>

<hr />

<c:set var="tokens" value="빨강색, 주황색. 노란색, 파랑색, 남색. 보라색" />
<c:forTokens var="token" items="${ tokens }" delims=",.">
	${ token }<br />
</c:forTokens>

</body>
</html>