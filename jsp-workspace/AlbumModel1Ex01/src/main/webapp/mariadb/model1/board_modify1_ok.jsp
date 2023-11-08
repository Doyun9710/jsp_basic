<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model1.BoardTO" %>
<%@ page import="model1.BoardDAO" %>

<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="java.io.File" %>

<%
	String uploadPath = "C:/Java/jsp-workspace/AlbumModel1Ex01/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";
	
	MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );

	BoardTO to = new BoardTO();
	to.setSeq( multi.getParameter( "seq" ) );
	to.setPassword( multi.getParameter( "password" ) );

	to.setSubject( multi.getParameter( "subject" ) );
	to.setMail( "" );
	if(!multi.getParameter( "mail1" ).equals( "" ) && !multi.getParameter( "mail2" ).equals( "" )) {
		to.setMail( multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" ) );
	}

	to.setContent( multi.getParameter( "content" ) );

	//String oldfilename = multi.getParameter( "filename_org" );
	to.setFilename( multi.getFilesystemName( "upload" ) );
	to.setFilesize( 0 );
	if( multi.getFile( "upload" ) != null ) {
		to.setFilesize( multi.getFile( "upload" ).length() );
	}

	BoardDAO dao = new BoardDAO();
	int flag = dao.boardModifyOk( to );

	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert( '글수정 성공' )" );
		out.println( "location.href='./board_list1.jsp';" );
	} else if( flag == 1 ) {
		out.println( "alert( '비밀번호 오류' )" );
		out.println( "history.back()" );
	} else if( flag == 2 ) {
		out.println( "alert( '시스템 에러' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>