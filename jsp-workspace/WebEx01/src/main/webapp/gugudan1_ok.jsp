<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	request.setCharacterEncoding( "utf-8");

	String startdan = request.getParameter( "startdan" );
	String enddan = request.getParameter( "enddan" );
	
	//System.out.println( startdan );
	//System.out.println( enddan );
	
	int iStartdan = Integer.parseInt(startdan);
	int iEnddan = Integer.parseInt(enddan);
	
	StringBuilder sbHtml = new StringBuilder();
	
	sbHtml.append( "<table border='1' width='800'" );
	for( int i=iStartdan ; i<=iEnddan ; i++ ) {
		sbHtml.append( "<tr>" );
		for( int j=1 ; j<=9 ; j++ ) {
			sbHtml.append( "<td>" + i + " X " + j + " = " + (i*j) + "</td>" );
		}
		sbHtml.append( "</tr>" );
	}
	sbHtml.append( "</table>" );
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- gugudan1_ok.jsp -->
<%=sbHtml %>
</body>
</html>