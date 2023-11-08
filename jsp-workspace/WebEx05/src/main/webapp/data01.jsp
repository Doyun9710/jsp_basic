<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String data1 = "value1";
	out.println( "data1 : " + data1 + "<br />" );
	
	// 기본 객체
	pageContext.setAttribute( "data2", "value2" );
	String data2 = (String)pageContext.getAttribute( "data2" );
	out.println( "data2 : " + data2 + "<br />" );
	
	pageContext.setAttribute( "data3", new java.util.Date() );
	java.util.Date date = (java.util.Date)pageContext.getAttribute( "data3" );
	out.println( "date : " + date + "<br />" );
%>