<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding( "utf-8" );
	
	response.setContentType( "text/html;charset=utf-8" );
	
	StringBuilder sbHtml = new StringBuilder();
	/*
	sbHtml.append( "<!doctype html>" );
	sbHtml.append( "<html>" );
	sbHtml.append( "<head>" );
	sbHtml.append( "<meta charset='utf-8' />" );
	sbHtml.append( "</head>" );
	sbHtml.append( "<body>" );
	*/
	sbHtml.append( "<form action='controller' method='post'>" );
	sbHtml.append( "<input type='hidden' name='path' value='gugudan_ok' />" );
	sbHtml.append( "시작단 : <input type='text' name='startdan' />" );
	sbHtml.append( " - " );
	sbHtml.append( "끝단 : <input type='text' name='enddan' />" );
	sbHtml.append( "&nbsp;&nbsp;<input type='submit' value='구구단 출력' />" );
	sbHtml.append( "</form>" );
	/*
	sbHtml.append( "</body>" );
	sbHtml.append( "</html>" );
	*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%= sbHtml %>

<br /><br />

<form action='controller' method='post'>
<input type='hidden' name='path' value='gugudan_ok' />
시작단 : <input type='text' name='startdan' /><br />
끝단 : <input type='text' name='enddan' /><br />
<input type='submit' value='구구단 출력' />
</form>

</body>
</html>