<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- setCookie.jsp -->
<%@ page import="java.net.URLEncoder" %>
<%
	Cookie cookie1 = new Cookie( "data1", URLEncoder.encode( "value1", "utf-8" ) );
	cookie1.setMaxAge( 2 * 60 );	// 2분
	response.addCookie( cookie1 );
	
	Cookie cookie2 = new Cookie( "data2", URLEncoder.encode( "값2", "utf-8" ) );
	cookie2.setMaxAge( 2 * 60 );
	response.addCookie( cookie2 );
%>
쿠키전송완료