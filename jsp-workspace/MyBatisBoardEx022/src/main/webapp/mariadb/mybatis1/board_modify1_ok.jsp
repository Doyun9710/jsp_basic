<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.exam.model1.BoardTO" %>
<%@ page import="com.exam.model1.BoardDAO" %>

<%
	request.setCharacterEncoding( "utf-8" );

	BoardTO to = new BoardTO();
	to.setSeq( request.getParameter( "seq" ) );
	to.setPassword( request.getParameter( "password" ) );
    
	to.setSubject( request.getParameter( "subject" ) );
	to.setMail( "" );
	if(!request.getParameter( "mail1" ).equals( "" ) && !request.getParameter( "mail2" ).equals( "" )) {
		to.setMail( request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" ) );
	}
	
	to.setContent( request.getParameter( "content" ) );
    
	BoardDAO dao = new BoardDAO();
	int flag = dao.boardModifyOk( to );
	
	String seq = to.getSeq();
	
	out.println( "<script type='text/javascript'>" );
	if(flag == 0) {
		out.println( "alert('글수정 성공');" );
		out.println( "location.href='board_view1.jsp?seq=" + seq + "';" );
	} else if(flag == 1) {
		out.println( "alert('비밀번호 오류');" );
		out.println( "history.back();" );
	} else {
		out.println( "alert('글수정 에러');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>