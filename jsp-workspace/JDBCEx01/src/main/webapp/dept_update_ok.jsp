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

	String url = "jdbc:mariadb://localhost:3306/sample";
	String user = "root";
	String password = "!123456";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	try {
		Class.forName( "org.mariadb.jdbc.Driver" );
		conn = DriverManager.getConnection(url, user, password);
		
		String sql = "update dept2 set dname=?, loc=? where deptno=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dname);
		pstmt.setString(2, loc);
		pstmt.setString(3, deptno);
		
		int result = pstmt.executeUpdate();
		
		out.println( "수정 성공 : " + result );
	} catch(ClassNotFoundException e) {
		System.out.println( "[에러]" + e.getMessage() );
	} catch(SQLException e) {
		System.out.println( "[에러]" + e.getMessage() );
	} finally {
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
%>