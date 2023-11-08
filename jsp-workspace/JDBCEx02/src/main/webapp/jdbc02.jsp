<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	StringBuilder sbHtml = new StringBuilder();
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env");
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb1" );
		
		conn = dataSource.getConnection();
		
		String sql = "select * from dept2";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		sbHtml.append( "<table border='1' width='400'>" );
		while( rs.next() ) {
			sbHtml.append( "<tr>" );

			sbHtml.append( "<td>" + rs.getString("deptno") + "</td>");
			sbHtml.append( "<td>" + rs.getString("dname") + "</td>");
			sbHtml.append( "<td>" + rs.getString("loc") + "</td>");
			
			sbHtml.append( "</tr>" );
		}
		sbHtml.append( "</table>" );
	} catch(NamingException e) {
		System.out.println( "[에러]" + e.getMessage() );
	} catch(SQLException e) {
		System.out.println( "[에러]" + e.getMessage() );
	} finally {
		if( rs != null ) rs.close();
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= sbHtml %>
</body>
</html>