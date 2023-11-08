<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	out.println( "남은 버퍼사이즈 : " + out.getRemaining() + "<br />" );
	out.println( "1<br />" );
	out.println( "2<br />" );
	out.println( "3<br />" );
	
	// buffer 비우기
	out.clearBuffer();
	out.println( "남은 버퍼사이즈 : " + out.getRemaining() + "<br />" );
	
	out.println( "4<br />" );
	out.println( "5<br />" );
	out.println( "6<br />" );
	
	// buffer 출력
	out.flush();
	out.println( "남은 버퍼사이즈 : " + out.getRemaining() + "<br />" );
	
	out.println( "7<br />" );
	out.println( "8<br />" );
	
	// 죵료
	out.close();
	
	out.println( "9<br />" );
	out.println( "10<br />" );
%>