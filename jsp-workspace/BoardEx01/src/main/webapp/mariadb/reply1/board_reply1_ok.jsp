<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource"%>

<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException"%>

<%
	request.setCharacterEncoding( "utf-8" );

	String pseq = request.getParameter( "pseq" );

	String subject = request.getParameter( "subject" );
	String writer = request.getParameter( "writer" );
	String mail = "";
	if( !request.getParameter( "mail1" ).equals("") 
			&& !request.getParameter( "mail2" ).equals("") ) {
		mail = request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" );
	}
	String password = request.getParameter( "password" );
	String content = request.getParameter( "content" );
	
	String wip = request.getRemoteAddr();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 정상 = 0, 비정상 = 1;
	int flag = 1;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		
		// 부모글 정보 SELECT
		String sql = "select grp, grps, grpl from rep_board1 where seq=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, pseq );
		
		rs = pstmt.executeQuery();
		
		int grp = 0;
		int grps = 0;
		int grpl = 0;
		
		if( rs.next() ) {
			grp = rs.getInt( "grp" );
			grps = rs.getInt( "grps" );
			grpl = rs.getInt( "grpl" );
		}
		
		
		// grps UPDATE
		// 부모글 grps 보다 큰 grps 번호 + 1
		sql = "update rep_board1 set grps=grps+1 where grp=? and grps>?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setInt( 1, grp );
		pstmt.setInt( 2, grps );
		
		pstmt.executeUpdate();
		
		
		// INSERT
		sql = "insert into rep_board1 values ( 0, ?, ?, ?, ?, ?, ?, ?, ?, 0, ?, now() )";
		pstmt = conn.prepareStatement( sql );
		pstmt.setInt( 1, grp );
		pstmt.setInt( 2, grps + 1 );	// 부모글 grps + 1
		pstmt.setInt( 3, grpl + 1 );	// 부모글 grpl + 1
		
		pstmt.setString( 4, subject );
		pstmt.setString( 5, writer );
		pstmt.setString( 6, mail );
		pstmt.setString( 7, password );
		pstmt.setString( 8, content );
		pstmt.setString( 9, wip );
		
		if( pstmt.executeUpdate() == 1 ) {
			flag = 0;
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
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		// 정상
		out.println( "alert( '글쓰기 성공' )" );
		out.println( "location.href='./board_list1.jsp';" );
	} else if( flag == 1 ) {
		// 에러
		out.println( "alert( '글쓰기 실패' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>