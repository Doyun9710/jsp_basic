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

<c:set var="data" value="홍길동" />

<c:set var="data1" value="홍길동" scope="page" />
<c:set var="data2" value="김철수" scope="request" />
<c:set var="data3" value="임꺽정" scope="session" />
<c:set var="data4" value="장길산" scope="application" />

출력 : ${ pageScope.data }<br />
출력 : ${ requestScope.data }<br />
출력 : ${ sessionScope.data }<br />
출력 : ${ applicationScope.data }<br />

<br /><br />

출력 : ${ pageScope.data1 }<br />
출력 : ${ requestScope.data2 }<br />
출력 : ${ sessionScope.data3 }<br />
출력 : ${ applicationScope.data4 }<br />

</body>
</html>