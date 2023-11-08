<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>
<%@ page import="model1.BoardDAO" %>

<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="java.io.File" %>

<%
	String uploadPath = "C:/Java/jsp-workspace/PDSModel1Ex01/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";
	
	MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );

	BoardTO to = new BoardTO();
	
	to.setSubject( multi.getParameter( "subject" ) );
	to.setWriter( multi.getParameter( "writer" ) );
	to.setMail( "" );
	if( !multi.getParameter( "mail1" ).equals("") && !multi.getParameter( "mail2" ).equals("") ) {
		to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
	}
	to.setPassword( multi.getParameter( "password" ) );
	to.setContent( multi.getParameter( "content" ) );
	
	to.setWip( request.getRemoteAddr() );

	to.setFilename( multi.getFilesystemName( "upload" ) );
	to.setFilesize( 0 );
	if( multi.getFile( "upload" ) != null ) {
		to.setFilesize( multi.getFile( "upload" ).length() );
	}

	BoardDAO dao = new BoardDAO();
	int flag = dao.boardWriteOk( to );
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		// 정상
		out.println( "alert( '글쓰기 성공' )" );
		out.println( "location.href='./board_list1.jsp';" );
	} else if( flag == 1 ) {
		// 에러
		out.println( "alert( '글쓰기 실패' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>