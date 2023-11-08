<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer="4kb" autoFlush="false" %>
<%
	out.println( "버퍼 사이즈 : " + out.getBufferSize() + "<br />" );
	out.println( "남은 버퍼사이즈 : " + out.getRemaining() + "<br />" );
	out.println( "autoflush : " + out.isAutoFlush() + "<br />" );
	
	// buffer="1kb" autoFlush="false" => Error
	for( int i=1 ; i<100 ; i++ ) {
		out.println( i + "Hello JSP <br />" );
		
		if( i%50 == 0 ) {
			out.println( "남은 버퍼사이즈 : " + out.getRemaining() + "<br />" );
		}
	}
%>