<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if( session.getAttribute( "sid" ) == null || session.getAttribute( "sgrade" ) == null ) {
		// 로그인 안함
		out.println( "<script type='text/javascript'>" );
		out.println( "alert('로그인 필요')" );
		out.println( "location.href='login_form.jsp';" );
		out.println( "</script>" );
	} else {
		// 로그인 함
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- login_complete.jsp -->
로그인 후 보여질 페이지<br />
세션 아이디 : <%= (String)session.getAttribute( "sid" ) %><br />
세션 등급 : <%= (String)session.getAttribute( "sgrade" ) %><br />
<br /><br />
<a href="logout_ok.jsp">로그아웃</a>
</body>
</html>
<%
	}
%>