<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name1 = "홍길동";
	pageContext.setAttribute( "name2", "김철수" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	out.println( name1 + "<br />" );
%>
이름 : <%= name1 %><br />
이름 : ${ name1 }<br />
이름 : ${ name2 }<br />
<%
	out.println( pageContext.getAttribute( "name2" ) + "<br />" );
%>
${ pageScope.name2 }<br />
${ pageScope['name2'] }<br />
</body>
</html>