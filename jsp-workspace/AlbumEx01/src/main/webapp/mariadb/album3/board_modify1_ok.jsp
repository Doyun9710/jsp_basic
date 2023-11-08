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

<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="java.io.File" %>

<%
	String uploadPath = "C:/Java/jsp-workspace/AlbumEx01/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";
	
	MultipartRequest multi = new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );
	
	String cpage = multi.getParameter( "cpage" );

	String seq = multi.getParameter( "seq" );
	String subject = multi.getParameter( "subject" );
	String password = multi.getParameter( "password" );
	String mail = "";
	if( !multi.getParameter( "mail1" ).equals( "" ) 
			&& !multi.getParameter( "mail2" ).equals( "" ) ) {
		mail = multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" );
	}
	String content = multi.getParameter( "content" );

	//String oldfilename = multi.getParameter( "oldfilename" );
	String newfilename = multi.getFilesystemName( "upload" );
	long newfilesize = 0;
	if( multi.getFile( "upload" ) != null ) {
		newfilesize = multi.getFile( "upload" ).length();
	}
	
	//System.out.println( seq + "\t" + subject + "\t" + password + "\t" +  "\t" + newfilename );
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 2 : 시스템 에러, 1 : 비밀번호 에러, 0 : 정상
	int flag = 2;
	
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		
		String sql = "select filename from img_board1 where seq=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, seq );
		
		rs = pstmt.executeQuery();
		
		String oldfilename = null;
		if( rs.next() ) {
			oldfilename = rs.getString( "filename" );
		}
		
		if( newfilename != null ) {
			// 새로운 파일 있음
			sql = "update img_board1 set subject=?, mail=?, content=?, filename=?, filesize=? where seq=? and password=?";
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, subject );
			pstmt.setString( 2, mail );
			pstmt.setString( 3, content );
			pstmt.setString( 4, newfilename );
			pstmt.setLong( 5, newfilesize );
			pstmt.setString( 6, seq );
			pstmt.setString( 7, password );
		} else {
			// 새로운 파일 없음
			sql = "update img_board1 set subject=?, mail=?, content=? where seq=? and password=?";
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, subject );
			pstmt.setString( 2, mail );
			pstmt.setString( 3, content );
			pstmt.setString( 4, seq );
			pstmt.setString( 5, password );
		}

		int result = pstmt.executeUpdate();
		if( result == 0 ) {
			// 비밀번호 오류
			flag = 1;
			
			// 잘못 업로드 된 파일 삭제
			if( newfilename != null ) {
				File file = new File( uploadPath, newfilename );
				file.delete();
			}
		} else if( result == 1 ) {
			// 정상
			flag = 0;
			
			// 구형 파일 존재 시 삭제
			//if( newfilename != null && oldfilename != null ) {
			if( oldfilename != null ) {
				File file = new File( uploadPath, oldfilename );
				file.delete();
			}
		}
	} catch( NamingException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} catch(SQLException e) {
		System.out.println( "[에러] " + e.getMessage() );
	} finally {
		if( rs != null ) rs.close();
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		// 정상
		out.println( "alert( '글수정 성공' )" );
		out.println( "location.href='./board_list1.jsp?cpage=" + cpage + "';" );
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