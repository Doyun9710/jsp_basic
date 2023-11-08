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
	private String uploadPath = "C:/Java/jsp-workspace/AlbumCmtEx02/src/main/webapp/upload";
	
	public BoardDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
			this.dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb3" );
		} catch (NamingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}

	public void boardWrite() {}
	
	public int boardWriteOk(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int flag = 1;

		try {
			conn = this.dataSource.getConnection();
			
			String sql = "insert into img_board22 values ( 0, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?, now() )";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSubject() );
			pstmt.setString( 2, to.getWriter() );
			pstmt.setString( 3, to.getMail() );
			pstmt.setString( 4, to.getPassword() );
			pstmt.setString( 5, to.getContent() );
			pstmt.setString( 6, to.getFilename() );
			pstmt.setLong( 7, to.getFilesize() );
			
			pstmt.setString( 8, to.getLatitude() );
			pstmt.setString( 9, to.getLongitude() );
			
			pstmt.setString( 10, to.getWip() );
			
			if( pstmt.executeUpdate() == 1 ) {
				flag = 0;
			}
			
			
			String curseq = null;
			sql = "select seq from img_board22 order by seq desc limit 1";
			pstmt = conn.prepareStatement( sql );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				curseq = rs.getString( "seq" );
			}

			System.out.println( "확인확인확인 : " + curseq );
			
			sql = "insert into rep_board22 values ( 0, ?, 0, 0, 0, now() )";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, curseq );
			
			if( pstmt.executeUpdate() == 1 ) {
				flag = 0;
			}
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try{ pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try{ conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}
	
	public BoardListTO boardList(BoardListTO listTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// paging
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();
		
		try {
			conn = this.dataSource.getConnection();

			String sql = "select seq, subject, writer, filename, filesize, latitude, longitude, cmtcnt, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from img_board22 order by seq desc";
			pstmt = conn.prepareStatement( sql );
			
			rs = pstmt.executeQuery();
			
			rs.last();
			listTO.setTotalRecord( rs.getRow() );
			rs.beforeFirst();
			
			listTO.setTotalPage( ( ( listTO.getTotalRecord() - 1 ) / recordPerPage ) + 1);
			
			int skip = ( cpage - 1 ) * recordPerPage;
			if( skip != 0 ) rs.absolute( skip );
			
			ArrayList<BoardTO> boardLists = new ArrayList<BoardTO>();
			for( int i=0 ; i<recordPerPage && rs.next() ; i++ ) {
				BoardTO to = new BoardTO();
				
				to.setSeq( rs.getString( "seq" ) );
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setFilename( rs.getString( "filename" ) );
				to.setFilesize( rs.getLong( "filesize" ) );
				to.setLatitude( rs.getString( "latitude" ) );
				to.setLongitude( rs.getString( "longitude" ) );
				to.setRecount( rs.getLong( "cmtcnt" ) );
				to.setWdate( rs.getString( "wdate" ) );
				to.setHit( rs.getString( "hit" ) );
				to.setWgap( rs.getInt( "wgap" ) );
				
				boardLists.add( to );
			}
			
			listTO.setBoardLists( boardLists );
			
			listTO.setStartBlock( cpage - ( cpage-1 ) % blockPerPage );
			listTO.setEndBlock( cpage - ( cpage-1 ) % blockPerPage + blockPerPage - 1 );
			
			if( listTO.getEndBlock() >= listTO.getTotalPage() ) {
				listTO.setEndBlock( listTO.getTotalPage() );
			}
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return listTO;
	}
	
	public BoardTO boardView(BoardTO to) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dataSource.getConnection();

			String sql = "update img_board22 set hit=hit+1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			pstmt.executeUpdate();
			
			
			// 다음글 / 이전글
			sql = "select seq, subject from img_board22 where seq > ? order by seq asc limit 1";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				to.setNextseq( rs.getString( "seq" ) );
				to.setNextsub( rs.getString( "subject" ) );
			}
			sql = "select seq, subject from img_board22 where seq < ? order by seq desc limit 1";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				to.setBeseq( rs.getString( "seq" ) );
				to.setBesub( rs.getString( "subject" ) );
			}
			
			
			sql = "select subject, writer, mail, wip, wdate, hit, content, filename, filesize, latitude, longitude from img_board22 where seq=?";
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
				
				to.setLatitude( rs.getString( "latitude" ) );
				to.setLongitude( rs.getString( "longitude" ) );
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
			
			String sql = "select subject, writer, mail, content, filename, latitude, longitude from img_board22 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				to.setSubject( rs.getString( "subject" ) );
				to.setWriter( rs.getString( "writer" ) );
				to.setMail( rs.getString( "mail" ) );
				to.setContent( rs.getString( "content" ) );
				to.setFilename( rs.getString( "filename" ) );
				
				to.setLatitude( rs.getString( "latitude" ) );
				to.setLongitude( rs.getString( "longitude" ) );
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
			conn = this.dataSource.getConnection();
			
			String sql = "select filename from img_board22 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			String oldfilename = null;
			if( rs.next() ) {
				oldfilename = rs.getString( "filename" );
			}
			
			if( to.getFilename() != null ) {
				// 새로운 파일 있음
				sql = "update img_board22 set subject=?, mail=?, content=?, filename=?, filesize=?, latitude=?, longitude=? where seq=? and password=?";
				pstmt = conn.prepareStatement( sql );
				
				pstmt.setString( 1, to.getSubject() );
				pstmt.setString( 2, to.getMail() );
				pstmt.setString( 3, to.getContent() );
				pstmt.setString( 4, to.getFilename() );
				pstmt.setLong( 5, to.getFilesize() );

				pstmt.setString( 6, to.getLatitude() );
				pstmt.setString( 7, to.getLongitude() );

				pstmt.setString( 8, to.getSeq() );
				pstmt.setString( 9, to.getPassword() );
			} else {
				// 새로운 파일 없음
				sql = "update img_board22 set subject=?, mail=?, content=? where seq=? and password=?";
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
			
			String sql = "select subject, writer from img_board22 where seq=?";
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

			String sql = "select filename from img_board22 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			to.setFilename( null );
			if( rs.next() ) {
				to.setFilename( rs.getString( "filename" ) );
			}

			sql = "delete from img_board22 where seq=? and password=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			pstmt.setString( 2, to.getPassword() );
			
			int result = pstmt.executeUpdate();
			
			if( result == 0 ) {
				flag = 1;
			} else if( result == 1 ) {
				flag = 0;

				// 실제 파일 삭제
				if( to.getFilename() != null ) {
					File file = new File( uploadPath, to.getFilename() );
					file.delete();
				}
			}
			
			sql = "delete from rep_board22 where pseq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			int result1 = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}

	// 댓글 추가 rep_board22 INSERT
	public int boardReplyOk(BoardReplyTO rto) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int flag = 1;
		
		try {
			conn = dataSource.getConnection();

			// INSERT
			String sql = "insert into rep_board22 values ( 0, ?, ?, ?, ?, now() )";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, rto.getSeq() );
			pstmt.setString( 2, rto.getCwriter() );
			pstmt.setString( 3, rto.getCpassword() );
			pstmt.setString( 4, rto.getCcontent() );

			if( pstmt.executeUpdate() == 1 ) {
				flag = 0;
			}
			
			
			sql = "update img_board22 set cmtcnt=cmtcnt+1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, rto.getSeq() );
			
			pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println( "[에러] + " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return flag;
	}

	// 댓글 불러오기 -> view.do(board_view.jsp)
	public BoardReplyTO boardReplyList(BoardReplyTO listRTO) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dataSource.getConnection();

			String sql = "select writer, content, date_format(wdate, '%Y-%m-%d') wdate from rep_board22 where pseq=? order by seq desc";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, listRTO.getSeq() );
			rs = pstmt.executeQuery();

			ArrayList<BoardReplyTO> boardReplyLists = new ArrayList<BoardReplyTO>();
			while( rs.next() ) {
				BoardReplyTO rto = new BoardReplyTO();
				
				rto.setCwriter( rs.getString( "writer" ) );
				rto.setCcontent( rs.getString( "content" ) );
				rto.setCdate( rs.getString( "wdate" ) );
				
				boardReplyLists.add( rto );
			}
			
			listRTO.setBoardReplyLists( boardReplyLists );
		} catch( SQLException e ) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}

		return listRTO;
	}

}
