<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- forword_ok.jsp -->
<%
	request.setCharacterEncoding( "utf-8" );

	String url = request.getParameter( "url" );
	
	// <jsp:forward>
	RequestDispatcher dispatcher = request.getRequestDispatcher( "./design/" + url );
	dispatcher.forward( request, response );
%>