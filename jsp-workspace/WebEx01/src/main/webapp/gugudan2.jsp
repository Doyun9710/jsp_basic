<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding( "utf-8");

	StringBuilder sbHtml = new StringBuilder();

	if( request.getParameter( "startdan") != null && request.getParameter( "enddan" ) != null ) {
		String startdan = request.getParameter( "startdan" );
		String enddan = request.getParameter( "enddan" );
	
		
		int iStartdan = Integer.parseInt(startdan);
		int iEnddan = Integer.parseInt(enddan);

		sbHtml.append( "<table border='1' width='800'" );
		for( int i=iStartdan ; i<=iEnddan ; i++ ) {
			sbHtml.append( "<tr>" );
			for( int j=1 ; j<=9 ; j++ ) {
				sbHtml.append( "<td>" + i + " X " + j + " = " + (i*j) + "</td>" );
			}
			sbHtml.append( "<tr>" );
		}
		sbHtml.append( "</table>" );
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./gugudan2.jsp" method="post">
	시작단 <input type="text" name="startdan" />
	끝단 <input type="text" name="enddan" />
	<input type="submit" value="구구단 출력" />
</form>

<br /><br /><br />

<%=sbHtml.toString() %>

</body>
</html>