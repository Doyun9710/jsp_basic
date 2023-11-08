<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>

<%
	/* // ERROR
	request.setCharacterEncoding( "utf-8" );
	out.println( request.getParameter( "data1" ) + "<br />" );
	out.println( request.getParameter( "data2" ) + "<br />" );
	*/
	
	String uploadPath = "C:/Java/jsp-workspace/UploadEx01/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";

	MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );
	out.println( "전송완료" + "<br />" );
	
	out.println( multi.getParameter( "data1" ) + "<br />" );
	out.println( multi.getParameter( "data1" ) + "<br />" );
	
	out.println( request.getRemoteAddr() );	// IP
%>