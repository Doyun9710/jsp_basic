<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	out.println( application.getInitParameter( "logEnabled" ) + "<br/>" );
	out.println( application.getInitParameter( "debugLevel" ) + "<br/>" );
%>