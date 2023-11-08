<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException"%>

<%
	// 100개의 데이터 강제 입력
	request.setCharacterEncoding( "utf-8" );

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	// 정상 = 0, 비정상 = 1;
	int flag = 1;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		
		//String sql = "insert into board1 values ( 0, '제목', '이름', 'test@test.com', '1234', '내용', 0, '000.000.000.000', now() )";
		String sql = "insert into board1 values ( 0, ?, ?, ?, ?, ?, 0, ?, now() )";
		pstmt = conn.prepareStatement( sql );
		
		for( int i=1 ; i<=100 ; i++ ) {
			pstmt.setString( 1, "제목" + i );
			pstmt.setString( 2, "이름" + i );
			pstmt.setString( 3, "test@test.com" );
			pstmt.setString( 4, "1234" );
			pstmt.setString( 5, "내용" + i );
			pstmt.setString( 6, "000.000.000.000" );
			
			pstmt.executeUpdate();
		}

	} catch( NamingException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} catch(SQLException e) {
		System.out.println( "[에러] + " + e.getMessage() );
	} finally {
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
	
	out.println( "<script type='text/javascript'>" );
	out.println( "alert( '글쓰기 성공' )" );
	out.println( "location.href='./board_list1.jsp';" );
	out.println( "</script>" );
%>