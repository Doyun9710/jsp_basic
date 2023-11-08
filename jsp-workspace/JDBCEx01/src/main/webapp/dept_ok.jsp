<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.PreparedStatement" %>

<%
	request.setCharacterEncoding( "utf-8" );

	String deptno = request.getParameter( "deptno" );
	String dname = request.getParameter( "dname" );
	String loc = request.getParameter( "loc" );
	
	//out.println( deptno );
	//out.println( dname );
	//out.println( loc );
	
	String url = "jdbc:mariadb://localhost:3306/sample";
	String user = "root";
	String password = "!123456";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	int flag = 1;
	
	try {
		Class.forName( "org.mariadb.jdbc.Driver" );
		conn = DriverManager.getConnection(url, user, password);
		
		String sql = "insert into dept2 value ( ?, ?, ? )";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, deptno);
		pstmt.setString(2, dname);
		pstmt.setString(3, loc);
		
		int result = pstmt.executeUpdate();
		
		//out.println( "입력 성공 : " + result );
		flag = 0;
	} catch(ClassNotFoundException e) {
		System.out.println( "[에러]" + e.getMessage() );
	} catch(SQLException e) {
		System.out.println( "[에러]" + e.getMessage() );
	} finally {
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
	
	//response.sendRedirect( "./dept.jsp" );
	
	out.println( "<script type='text/javascript'>");
	if( flag == 0 ) {
		// 정상
		out.println( "alert( '정상입력' );" );
		out.println( "location.href='./dept.jsp';" );
	} else if( flag == 1 ) {
		// 에러
		out.println( "alert( '에러' );" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>