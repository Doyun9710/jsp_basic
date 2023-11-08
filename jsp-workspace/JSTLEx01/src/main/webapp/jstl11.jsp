<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="to1" value="<%= new model1.BoardTO() %>" />
<c:set target="${ to1 }" property="subject" value="제목1" />
<c:set target="${ to1 }" property="writer" value="작성자1" />

<c:set var="to2" value="<%= new model1.BoardTO() %>" />
<c:set target="${ to2 }" property="subject" value="제목2" />
<c:set target="${ to2 }" property="writer" value="작성자2" />

<c:set var="lists" value="<%= new java.util.ArrayList() %>" />
<c:set target="noUse" value="${ lists.add(to1) }" />
<c:set target="noUse" value="${ lists.add(to2) }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- EL / JSTL -->
<c:forEach var="to" items="${ lists }">
	${ to.subject }<br />
	${ to.writer }<br />
</c:forEach>

</body>
</html>