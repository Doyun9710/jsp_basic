package com.exam.model1;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataSource = null;
	private String uploadPath = "C:/Java/jsp-workspace/PDSModel2Ex01/src/main/webapp/upload";
	
	public BoardDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		} catch (NamingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}
	
	// 각 페이지 당 메서드 1개
	public void boardWrite() {}
	
	public int boardWriteOk(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int flag = 1;

		try {
			conn = this.dataSource.getConnection();
			
			String sql = "insert into pds_board1 values ( 0, ?, ?, ?, ?, ?, ?, ?, 0, ?, now() )";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSubject() );
			pstmt.setString( 2, to.getWriter() );
			pstmt.setString( 3, to.getMail() );
			pstmt.setString( 4, to.getPassword() );
			pstmt.setString( 5, to.getContent() );
			pstmt.setString( 6, to.getFilename() );
			pstmt.setLong( 7, to.getFilesize() );
			pstmt.setString( 8, to.getWip() );
			
			if( pstmt.executeUpdate() == 1 ) {
				flag = 0;
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( pstmt != null ) try{ pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try{ conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}
	
	public ArrayList<BoardTO> boardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<BoardTO> datas = new ArrayList<BoardTO>();
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "select seq, subject, writer, filesize, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from pds_board1 order by seq desc";
			pstmt = conn.prepareStatement( sql );
			
			rs = pstmt.executeQuery();

			while( rs.next() ) {
				BoardTO to = new BoardTO();
				to.setSeq( rs.getString( "seq" ) );
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setWdate( rs.getString( "wdate" ) );
				to.setHit( rs.getString( "hit" ) );
				to.setWgap( rs.getInt( "wgap" ) );

				datas.add( to );
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
	
	public BoardTO boardView(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.dataSource.getConnection();

			String sql = "update pds_board1 set hit=hit+1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			pstmt.executeUpdate();
			
			sql = "select subject, writer, mail, wip, wdate, hit, content, filename, filesize from pds_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setMail( rs.getString( "mail" ) );
				to.setWip( rs.getString( "wip" ) );
				to.setWdate( rs.getString( "wdate" ) );
				to.setHit( rs.getString( "hit" ) );
				to.setContent( rs.getString( "content" ) == null ? "" : rs.getString( "content" ).replaceAll("\n", "<br />") );
				
				if( rs.getString( "filename" ) != null ) to.setFilename( rs.getString( "filename" ) );
				else to.setFilename( "없음" );
				to.setFilesize( rs.getLong( "filesize" ) / 1024 );
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return to;
	}
	
	public BoardTO boardModify(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dataSource.getConnection();
			
			String sql = "select subject, writer, mail, content, filename from pds_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setMail( rs.getString( "mail" ) );
				to.setContent( rs.getString( "content" ) );
				to.setFilename( rs.getString( "filename" ) );
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return to;
	}
	
	public int boardModifyOk(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
			
			conn = dataSource.getConnection();
			
			String sql = "select filename from pds_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			String oldfilename = null;
			if( rs.next() ) {
				oldfilename = rs.getString( "filename" );
			}
			
			if( to.getFilename() != null ) {
				// 새로운 파일 있음
				sql = "update pds_board1 set subject=?, mail=?, content=?, filename=?, filesize=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				
				pstmt.setString( 1, to.getSubject() );
				pstmt.setString( 2, to.getMail() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getFilename() );
				pstmt.setLong( 5, to.getFilesize() );
				pstmt.setString( 6, to.getSeq() );
				pstmt.setString( 7, to.getPassword() );
			} else {
				// 새로운 파일 없음
				sql = "update pds_board1 set subject=?, mail=?, content=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				
				pstmt.setString( 1, to.getSubject() );
				pstmt.setString( 2, to.getMail() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getSeq() );
				pstmt.setString( 5, to.getPassword() );
			}

			int result = pstmt.executeUpdate();
			if( result == 0 ) {
				flag = 1;
				
				// 잘못 업로드 된 파일 삭제
				if( to.getFilename() != null ) {
					File file = new File( uploadPath, to.getFilename() );
					file.delete();
				}
			} else if( result == 1 ) {
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
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}
	
	public BoardTO boardDelete(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "select subject, writer from pds_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try{ rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try{ pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try{ conn.close(); } catch(SQLException e) {}
		}
		
		return to;
	}
	
	public int boardDeleteOk(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 2;
		
		try {
			conn = dataSource.getConnection();

			String sql = "select filename from pds_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			to.setFilename( null );
			if( rs.next() ) {
				to.setFilename( rs.getString( "filename" ) );
			}

			sql = "delete from pds_board1 where seq=? and password=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			pstmt.setString( 2, to.getPassword() );
			
			int result = pstmt.executeUpdate();
			
			if( result == 0 ) {
				flag = 1;	// 비밀번호 오류
			} else if( result == 1 ) {
				flag = 0;	// 정상
				System.out.println( "check 01" );
				// 실제 파일 삭제
				if( to.getFilename() != null ) {
					File file = new File( uploadPath, to.getFilename() );
					file.delete();
					System.out.println( "check 02" );
				}
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}
}
