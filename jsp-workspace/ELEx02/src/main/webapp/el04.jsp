<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	// 영역 작은 순서, ${ name } 대상 우선순위
	pageContext.setAttribute( "name", "홍길동" );
	request.setAttribute( "name", "김철수" );
	session.setAttribute( "name", "임꺽정" );
	application.setAttribute( "name", "장길산" );
%>
<br /><br /><br />
<%= pageContext.getAttribute( "name" ) %><br />
<%= request.getAttribute( "name" ) %><br />
<%= session.getAttribute( "name" ) %><br />
<%= application.getAttribute( "name" ) %><br />
<!-- 변수 선언이 없어도 에러가 없음 -->
${ name }<br />
<%-- <%= name2 %> --%>
<br /><br /><br />
${ pageScope.name }<br />
${ requestScope.name }<br />
${ sessionScope.name }<br />
${ applicationScope.name }<br />

</body>
</html>