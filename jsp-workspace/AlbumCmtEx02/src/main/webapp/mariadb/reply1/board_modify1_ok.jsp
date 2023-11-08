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
	String subject = request.getParameter( "subject" );
	String password = request.getParameter( "password" );
	String mail = "";
	if( !request.getParameter( "mail1" ).equals( "" ) 
			&& !request.getParameter( "mail2" ).equals( "" ) ) {
		mail = request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" );
	}
	String content = request.getParameter( "content" );

	Connection conn = null;
	PreparedStatement pstmt = null;
	
	// 2 : 시스템 에러, 1 : 비밀번호 에러, 0 : 정상
	int flag = 2;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		
		// 비밀번호를 프로그램으로 가져오지 말 것
		// 비밀번호는 암호화
		String sql = "update rep_board1 set subject=?, mail=?, content=? where seq=? and password=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, subject );
		pstmt.setString( 2, mail );
		pstmt.setString( 3, content );
		pstmt.setString( 4, seq );
		pstmt.setString( 5, password );
		
		int result = pstmt.executeUpdate();
		
		if( result == 0 ) {
			// 비밀번호 오류
			flag = 1;
		} else if( result == 1 ) {
			// 정상
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
		out.println( "alert( '글수정 성공' )" );
		out.println( "location.href='./board_list1.jsp';" );
		//out.println( "location.href='./board_list1.jsp?cpage=" + cpage + "';" );
	} else if( flag == 1 ) {
		// 비밀번호 오류
		out.println( "alert( '비밀번호 오류' )" );
		out.println( "history.back()" );
	} else if( flag == 2 ) {
		// 에러
		out.println( "alert( '시스템 에러' )" );
		out.println( "history.back()" );
	}
	out.println( "</script>" );
%>