﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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

	String cpage = request.getParameter( "cpage" );

	String seq = request.getParameter( "seq" );
	String subject = "";
	String writer = "";
	String mail = "";
	String wip = "";
	String wdate = "";
	String hit = "";
	String content = "";
	
	String filename = "";
	long filesize = 0;
	
	//String file = "";
	
	// 다음글 / 이전글
	String nextseq = "";
	String nextsub = "다음글이 없습니다.";
	String beseq = "";
	String besub = "이전글이 없습니다.";
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	StringBuilder sbHtml = new StringBuilder();
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		
		conn = dataSource.getConnection();
		
		// 조회수 증가
		String sql = "update img_board1 set hit=hit+1 where seq=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, seq );
		
		pstmt.executeUpdate();
		
		
		// 다음글 / 이전글
		sql = "select seq, subject from img_board1 where seq > ? order by seq asc limit 1";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, seq );
		rs = pstmt.executeQuery();
		if( rs.next() ) {
			nextseq = rs.getString( "seq" );
			nextsub = rs.getString( "subject" );
		}
		
		sql = "select seq, subject from img_board1 where seq < ? order by seq desc limit 1";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, seq );
		rs = pstmt.executeQuery();
		if( rs.next() ) {
			beseq = rs.getString( "seq" );
			besub = rs.getString( "subject" );
		}
		
		
		sql = "select subject, writer, mail, wip, wdate, hit, content, filename, filesize from img_board1 where seq=?";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, seq );
		
		rs = pstmt.executeQuery();
		
		if( rs.next() ) {
			subject = rs.getString( "subject" );
			writer = rs.getString( "writer" );
			mail = rs.getString( "mail" );
			wip = rs.getString( "wip" );
			wdate = rs.getString( "wdate" );
			hit = rs.getString( "hit" );
			content = rs.getString( "content" ) == null ? "" : rs.getString( "content" ).replaceAll("\n", "<br />");
			
			//filename = rs.getString( "filename" );
			if( rs.getString( "filename" ) != null ) filename = rs.getString( "filename" );
			else filename = "없음";
			filesize = rs.getLong( "filesize" ) / 1024;
			/*
			if( filesize != 0 )
				file = filename + "(" + filesize + "kbyte)";
			*/
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
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/board_view.css">
</head>

<body>
<!-- 상단 디자인 -->
<div class="contents1"> 
	<div class="con_title"> 
		<p style="margin: 0px; text-align: right">
			<img style="vertical-align: middle" alt="" src="../../images/home_icon.gif" /> &gt; 커뮤니티 &gt; <strong>여행지리뷰</strong>
		</p>
	</div>

	<div class="contents_sub">	
	<!--게시판-->
		<div class="board_view">
			<table>
			<tr>
				<th width="10%">제목</th>
				<td width="60%"><%= subject %>(<%= wip %>)</td>
				<th width="10%">등록일</th>
				<td width="20%"><%= wdate %></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><%= writer %></td>
				<th>조회</th>
				<td><%= hit %></td>
			</tr>
			<tr>
				<td colspan="4" height="200" valign="top" style="padding:20px; line-height:160%">
					<div id="bbs_file_wrap">
						<div>
							<img src="../../upload/<%= filename %>" width="900" onerror="" /><br />
						</div>
					</div>
					<%= content %>
				</td>
			</tr>			
			</table>
		</div>
		<div class="btn_area">
			<div class="align_left">
				<input type="button" value="목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_list1.jsp?cpage=<%= cpage %>'" />
			</div>
			<div class="align_right">
				<input type="button" value="수정" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_modify1.jsp?cpage=<%= cpage %>&seq=<%= seq %>'" />
				<input type="button" value="삭제" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_delete1.jsp?cpage=<%= cpage %>&seq=<%= seq %>'" />
				<input type="button" value="쓰기" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='board_write1.jsp?cpage=<%= cpage %>'" />
			</div>
		</div>
		<!--//게시판-->
		
		<!-- 이전글 / 다음글 -->
		<div class="next_data_area">
			<span class="b">다음글 | </span><a href="board_view1.jsp?cpage=<%= cpage %>&seq=<%= nextseq %>"><%= nextsub %></a>
		</div>
		<div class="prev_data_area">
			<span class="b">이전글 | </span><a href="board_view1.jsp?cpage=<%= cpage %>&seq=<%= beseq %>"><%= besub %></a>
		</div>
		<!-- //이전글 / 다음글 -->
	</div>
<!-- 하단 디자인 -->
</div>

</body>
</html>
