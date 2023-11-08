<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- request2_ok -->
<%
	pageContext.setAttribute( "data2", "value2" );
	out.println( "data2 : " + (String)pageContext.getAttribute( "data2" ) + "<br />" );
	
	out.println( pageContext + "<br />" );
%>
<br /><hr /><br />
<%
	String data1 = request.getParameter( "data1" );
	out.println( "data1 : " + data1 + "<br />" );
	out.println( request );
%>
</body>
</html>