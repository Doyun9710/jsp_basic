<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>
<%@ page import="model1.BoardDAO" %>

<%
	request.setCharacterEncoding( "utf-8" );
	
	int flag = (Integer)request.getAttribute( "flag" );
	String seq = request.getParameter( "seq" );
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		// 정상
		out.println( "alert( '글수정 성공' )" );
		out.println( "location.href='./controller?path=list';" );
	} else if( flag == 1 ) {
		// 비밀번호 오류
		out.println( "alert( '비밀번호 오류' )" );
		out.println( "history.back()" );
	} else if( flag == 2 ) {
		// 에러
		out.println( "alert( '시스템 에러' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>