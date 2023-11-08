<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>

<%
	/*
	1. 업로드를 위한 서버경로
		C:/Java/jsp-workspace/UploadEx01/src/main/webapp/upload
	2. 업로드 제한 용량(byte)
		2M - 4 * 1024 * 1024
	3. 인코딩
		utf-8
	*/
	String uploadPath = "C:/Java/jsp-workspace/UploadEx01/src/main/webapp/upload";
	// 확인 - http://192.168.0.28:8080/UploadEx01/upload.jsp
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";
	
	/** DefaultFileRenamePolicy() : 파일 이름 중복 시 이름 변경 */
	MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );
	out.println( "전송완료" + "<br />" );
	
	out.println( multi.getFilesystemName("upload") + "<br />");		// 변경된 이름
	out.println( multi.getOriginalFileName("upload") + "<br />");	// 원본 이름
	
	// File Size
	java.io.File file = multi.getFile("upload");
	out.println( file.length() + "<br />" );
%>