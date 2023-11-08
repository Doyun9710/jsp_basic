<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
Hello Request1_sub.jsp<br />
<br /><hr /><br />
<%
	String data1 = request.getParameter( "data1" );
	out.println( "data1 : " + data1 + "<br />" );
	out.println( request );
	
	out.println( "data3 : " + request.getParameter( "data3" ) + "<br />" );
	out.println( "data4 : " + (String)request.getAttribute( "data4" ) + "<br />" );
%>