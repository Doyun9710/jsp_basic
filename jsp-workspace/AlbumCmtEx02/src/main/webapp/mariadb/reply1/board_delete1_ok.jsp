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
	request.setCharacterEncoding( "utf-8" );

	String cpage = request.getParameter( "cpage" );
	String seq = request.getParameter( "seq" );
	String password = request.getParameter( "password" );

	Connection conn = null;
	PreparedStatement pstmt = null;

	int flag = 2;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		/*
		String sql = "delete from rep_board1 where seq=? and password=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, seq );
		pstmt.setString( 2, password );
		*//*
		String sql = "update rep_board1 set subject=?, writer=?, mail=?, content=? where seq=? and password=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, "삭제된 글입니다." );
		pstmt.setString( 2, "삭제된 이용자" );
		pstmt.setString( 3, "null" );
		pstmt.setString( 4, "" );
		pstmt.setString( 5, password );
		*/
		String sql = "update rep_board1 set subject=? where seq=? and password=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, "삭제된 글입니다." );
		pstmt.setString( 2, seq );
		pstmt.setString( 3, password );
		
		int result = pstmt.executeUpdate();
		
		if( result == 0 ) {
			flag = 1;
		} else if( result == 1 ) {
			flag = 0;
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
	if( flag == 0 ) {
		out.println( "alert( '글삭제 성공' )" );
		out.println( "location.href='./board_list1.jsp';" );
		//out.println( "location.href='./board_list1.jsp?cpage=" + cpage + "';" );
	} else if( flag == 1 ) {
		out.println( "alert( '비밀번호 오류' )" );
		out.println( "history.back()" );
	} else if( flag == 2 ) {
		out.println( "alert( '시스템 에러' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>