<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>

<%
	request.setCharacterEncoding("utf-8");

	String url = "jdbc:mariadb://localhost:3306/sample";
	String user = "root";
	String password = "!123456";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	StringBuilder sbHtml = new StringBuilder();
	
	try {
		Class.forName( "org.mariadb.jdbc.Driver" );
		
		conn = DriverManager.getConnection(url, user, password);
		System.out.println( "연결성공" );
		
		String sql = "select * from dept";
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		sbHtml.append( "<table border='1'>" );
		while( rs.next() ) {
			sbHtml.append( "<tr>" );
			
			// System.out.println( rs.getString("deptno") );
			sbHtml.append( "<td>" + rs.getString("deptno") + "</td>");
			sbHtml.append( "<td>" + rs.getString("dname") + "</td>");
			sbHtml.append( "<td>" + rs.getString("loc") + "</td>");
			
			sbHtml.append( "</tr>" );
		}
		sbHtml.append( "</table>" );
	} catch(ClassNotFoundException e) {
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

<%= sbHtml.toString() %>

</body>
</html>