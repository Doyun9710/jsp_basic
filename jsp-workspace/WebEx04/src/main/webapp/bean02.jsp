<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="to" class="pack1.MemberTO" />

<%
	out.println( to );

	to.setId( "tester" );
	to.setPassword( "1234" );
	
	out.println( to.getId() + "<br />" );
	out.println( to.getPassword() + "<br />" );
%>