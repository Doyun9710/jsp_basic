<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.ZipcodeTO" %>
<%@ page import="java.util.ArrayList"%>

<%
	request.setCharacterEncoding( "utf-8" );

	String strdong = request.getParameter( "strdong" );

	ArrayList<ZipcodeTO> datas = (ArrayList)request.getAttribute( "datas" );

	StringBuilder sbHtml = new StringBuilder();
	
	sbHtml.append( "<table border='1'>" );
	for ( ZipcodeTO to : datas ) {
		String zipcode = to.getZipcode();
		String sido = to.getSido();
		String gugun = to.getGugun();
		String dong = to.getDong();
		String ri = to.getRi();
		String bunji = to.getBunji();
			
		sbHtml.append( "<tr>" );
		sbHtml.append( "<td>" + zipcode + "</td>" );
		sbHtml.append( "<td>" + sido + "</td>" );
		sbHtml.append( "<td>" + gugun+ "</td>" );
		sbHtml.append( "<td>" + dong + "</td>" );
		sbHtml.append( "<td>" + ri + "</td>" );
		sbHtml.append( "<td>" + bunji + "</td>" );
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

<!-- zipcode_ok.jsp -->
zipcode_ok.jsp
<br /><br />

<%=sbHtml %>

</body>
</html>