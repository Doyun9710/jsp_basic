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

<!-- login_ok.jsp -->
<%
	request.setCharacterEncoding( "utf-8" );

	String id = request.getParameter( "id" );
	String password = request.getParameter( "password" );

	// 저장된 아이디, 패스워드
	//String save_id = "tester";
	//String save_password = "1234";
	String save_id = null;
	String save_password = null;
	
	String name = null;
	String mail = null;
	String grade = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 2 : 시스템 에러, 1 : 비밀번호 에러, 0 : 정상
	int flag = 2;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
		
		conn = dataSource.getConnection();
		
		String sql = "select name, mail, grade from member1 where id=? and password=password(?)";
		//String sql = "select name, mail, grade from member1 where id=? and password=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, id );
		pstmt.setString( 2, password );
		
		rs = pstmt.executeQuery();

		if( rs.next() ) {
			name = rs.getString( "name" );
			mail = rs.getString( "mail" );
			grade = rs.getString( "grade" );
		}
		//System.out.println( name + "\t" + mail + "\t" + grade );

		session.setAttribute( "sid", id );
		session.setAttribute( "sgrade", grade );

		if( name != null )
			flag = 0;
		
	} catch( NamingException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} catch(SQLException e) {
		System.out.println( "[에러] " + e.getMessage() );
	} finally {
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
	
	/*
	// flag
	// 0 - 로그인
	// 1 - 아이디/비밀번호 오류
	// 2 - 기타 오류
	int flag = 2;
	if( save_id.equals( id ) && save_password.equals( password ) ) {
		// 성공
		flag = 0;
		
		// 세션 부여, 개인정보(X)
		session.setAttribute( "sid", id );
		session.setAttribute( "sgrade", "B" );
	} else {
		// 실패
		flag = 1;
	}
	*/
	
	// 리다이렉션
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert('로그인 되었습니다')" );
		out.println( "location.href='login_complete.jsp';" );
	} else if( flag == 1 ) {
		out.println( "alert('아이디 비밀번호 오류');" );
		out.println( "history.back();" );
	} else {
		out.println( "alert('기타 오류');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>