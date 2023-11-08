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

	String emot = request.getParameter( "emot" ).replaceAll( "emot", "" );
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
	
	// 정상 = 0, 비정상 = 1;
	int flag = 1;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		
		String sql = "insert into emot_board1 values ( 0, ?, ?, ?, ?, ?, ?, 0, ?, now() )";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, subject );
		pstmt.setString( 2, writer );
		pstmt.setString( 3, mail );
		pstmt.setString( 4, password );
		pstmt.setString( 5, content );
		pstmt.setString( 6, emot );
		pstmt.setString( 7, wip );
		
		if( pstmt.executeUpdate() == 1 ) {
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
		// 정상
		out.println( "alert( '글쓰기 성공' )" );
		//out.println( "location.href='./board_list1.jsp';" );
		out.println( "location.href='./board_list1.jsp?cpage=" + cpage + "';" );
	} else if( flag == 1 ) {
		// 에러
		out.println( "alert( '글쓰기 실패' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>