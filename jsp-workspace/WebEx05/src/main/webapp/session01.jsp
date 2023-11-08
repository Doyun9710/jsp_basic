<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %> %>
<%-- <%@ page session="false" %> --%>
<%
	out.println( "세션 아이디 : " + session.getId() + "<br />" );
	out.println( "유효시간 : " + session.getMaxInactiveInterval() + "<br />" );
	
	// 시간 변형
	out.println( "생성시간 : " + session.getCreationTime() + "<br />" );
	Date date = new Date();
	date.setTime( session.getCreationTime() );
	out.println( "생성시간 : " + date.toLocaleString() + "<br />" );
	
	// 최근 접속 시간
	out.println( "최근접속시간 : " + session.getLastAccessedTime() + "<br />" );
	date.setTime( session.getLastAccessedTime() );
	out.println( "생성시간 : " + date.toLocaleString() + "<br />" );
%>