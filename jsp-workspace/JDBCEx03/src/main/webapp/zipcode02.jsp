<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.sql.DataSource"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>

<% 
	request.setCharacterEncoding( "utf-8" );
	
	StringBuilder sbHtml = new StringBuilder();
	
	if(request.getParameter( "strdong" ) != null ) {
		String strDong = request.getParameter( "strdong" );
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
			
			conn = dataSource.getConnection();
			
			String sql = "select zipcode, sido, gugun, dong, ri, bunji from zipcode where dong like ?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, strDong + '%' );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				sbHtml.append( "<tr>" );
				sbHtml.append( "    <td>" + rs.getString( "zipcode" ) + "</td>" );
				sbHtml.append( "    <td>" + rs.getString( "sido" ) + "</td>" );
				sbHtml.append( "    <td>" + rs.getString( "gugun" ) + "</td>" );
				sbHtml.append( "    <td>" + rs.getString( "dong" ) + "</td>" );
				sbHtml.append( "    <td>" + rs.getString( "ri" ) + "</td>" );
				sbHtml.append( "    <td>" + rs.getString( "bunji" ) + "</td>" );
				sbHtml.append( "</tr>");
			}
			
		} catch( NamingException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch(SQLException e) {
			System.out.println( "[에러] + " + e.getMessage() );
		} finally {
			if( rs != null ) rs.close();
			if( pstmt != null ) pstmt.close();
			if( conn != null ) conn.close();
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		document.getElementById( 'btn' ).onclick= function() {
			document.frm.submit();
		};
	};
</script>
</head>
<body>

<form action="./zipcode02.jsp" method="post" name="frm">
동이름 <input type="text" name="strdong" />
<input type="button" id="btn" value="동이름 검색" />
</form>

<br /><br />

<table width="800" border="1">
<tr>
	<td>우편번호</td><td>시도</td><td>동</td><td>리</td><td>번지</td>
</tr>
<%=sbHtml.toString() %>
</table>

</body>
</html>