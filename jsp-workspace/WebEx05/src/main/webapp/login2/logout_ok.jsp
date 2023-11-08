<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- logout_ok.jsp -->
<%@ page import="util.Cookies" %>
<%
	response.addCookie( Cookies.createCookie( "cid", "", -1 ) );
	response.addCookie( Cookies.createCookie( "cgroup", "", -1 ) );

	out.println( "<script type='text/javascript'>" );
	out.println( "alert('로그아웃')" );
	out.println( "location.href='login_form.jsp';" );
	out.println( "</script>" );
%>