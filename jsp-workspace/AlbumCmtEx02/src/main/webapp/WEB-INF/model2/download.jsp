<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
    
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.net.URLEncoder" %>

<%
	String filename = request.getParameter( "filename" );
	String downPath = "C:/Java/jsp-workspace/AlbumCmtEx01/src/main/webapp/upload/" + filename;
	out.clearBuffer();
	response.setContentType( "application/octet-stream" );
	response.setHeader( "Content-Disposition", "attachment;filename=" + URLEncoder.encode( filename, "utf-8" ) );
	FileInputStream fis = new FileInputStream( downPath );
	ServletOutputStream sos = response.getOutputStream();
	byte[] b = new byte[4096];
	int data;
	while( ( data = fis.read( b, 0, b.length ) ) != -1 ) {
		sos.write( b, 0, data );
	}
	sos.flush();
	sos.close();
	fis.close();
%>