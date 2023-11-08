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

<c:set var="name" value="홍길동" />
<c:out value="${ name }" /><br />
<c:remove var="name" />
<c:out value="${ name }" default="이름 없음" /><br />

</body>
</html>