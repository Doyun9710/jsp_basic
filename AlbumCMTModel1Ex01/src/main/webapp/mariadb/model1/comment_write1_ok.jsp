<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="albumcmtmodel1.CommentTO" %>    
<%@ page import="albumcmtmodel1.CommentDAO" %>

<%
	request.setCharacterEncoding( "utf-8" );

	CommentTO to = new CommentTO();
	to.setPseq( request.getParameter( "seq" ) );
	to.setWriter( request.getParameter( "cwriter" ) );
	to.setPassword( request.getParameter( "cpassword" ) );
	to.setContent( request.getParameter( "ccontent" ) );

	CommentDAO dao = new CommentDAO();
	int flag = dao.commentWriteOk( to );

	String cpage = (String)request.getParameter( "cpage" );
	String seq = to.getPseq();
	
	out.println("<script type='text/javascript'>");
	if(flag == 0) {
		out.println( "alert('답글에 성공했습니다.');" );
		out.println( "location.href='board_view1.jsp?cpage=" + cpage + "&seq=" + seq + "';" );
	} else {
		out.println( "alert('글쓰기에 실패했습니다.');" );
		out.println( "history.back();");
	}
	out.println( "</script>" );
%>