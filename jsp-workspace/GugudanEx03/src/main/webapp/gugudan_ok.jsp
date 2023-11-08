<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding( "utf-8" );
	
	String startdan = request.getParameter("startdan") == null ? "" : request.getParameter("startdan");
	String enddan = request.getParameter("enddan") == null ? "" : request.getParameter("enddan");
	
	int iStartdan = Integer.parseInt( startdan );
	int iEnddan = Integer.parseInt( enddan );
	
	StringBuilder gugudanHtml = new StringBuilder();
	gugudanHtml.append( "<table border='1' width='800'>" );
	for( int i=iStartdan ; i<=iEnddan ; i++ ) {
		gugudanHtml.append( "<tr>" );
		for( int j=1 ; j<=9 ; j++ ) {
			gugudanHtml.append( "<td>" + i + " X " + j + " = " + ( i*j )+ "</td>" );
		}
		gugudanHtml.append( "</tr>" );
	}
	gugudanHtml.append( "</table>" );
	
	response.setContentType( "text/html;charset=utf-8" );
	/*
	StringBuilder sbHtml = new StringBuilder();
	
	sbHtml.append( "<!doctype html>" );
	sbHtml.append( "<html>" );
	sbHtml.append( "<head>" );
	sbHtml.append( "<meta charset='utf-8' />" );
	sbHtml.append( "</head>" );
	sbHtml.append( "<body>" );
	
	sbHtml.append( gugudanHtml );
	
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

<%= gugudanHtml %>

<br /><br />

<%= (String)request.getAttribute( "result" ) %>

</body>
</html>