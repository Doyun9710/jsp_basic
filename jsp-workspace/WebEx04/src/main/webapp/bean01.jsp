<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="pack1.MemberTO" %>

<%
	MemberTO to = new MemberTO();

	to.setId( "tester" );
	to.setPassword( "1234" );

	out.println( to.getId() + "<br />" );
	out.println( to.getPassword() + "<br />" );
%>