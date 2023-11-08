<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="util.Cookies" %>
<%
	response.addCookie( Cookies.createCookie( "data1", "value1" ) );
	response.addCookie( Cookies.createCookie( "data2", "value2" ) );
%>
쿠키전송완료