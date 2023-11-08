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

<c:set var="data1" value="홍길동" />
<c:set var="data2" >김철수</c:set>

출력 : ${ data1 }<br />
출력 : ${ data2 }<br />
출력 : <c:out value="${ data1 }" /><br />

</body>
</html>