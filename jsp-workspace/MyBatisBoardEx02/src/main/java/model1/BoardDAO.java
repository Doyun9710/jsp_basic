package model1;

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
	private String uploadPath = "C:/Java/jsp-workspace/MyBatisBoardEx01/src/main/webapp/upload";
	
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
			
			String sql = "insert into img_board1 values ( 0, ?, ?, ?, ?, ?, ?, ?, 0, ?, now() )";
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

			String sql = "select seq, subject, writer, filename, filesize, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from img_board1 order by seq desc";
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

			// 조회수 증가
			String sql = "update img_board1 set hit=hit+1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			pstmt.executeUpdate();
			
			
			// 다음글 / 이전글
			/* board.xml
			<select id="view_nextpage" parameterType="com.exam.model1.BoardTO" resultType="com.exam.model1.BoardTO">
				select seq, subject from img_board1 where seq > #{ seq }
				order by seq asc limit 1
			</select>
		
			<select id="view_lastpage" parameterType="com.exam.model1.BoardTO" resultType="com.exam.model1.BoardTO">
				select seq, subject from img_board1 where seq < #{ seq }
				order by seq desc limit 1
			</select>
			 */
			sql = "select seq, subject from img_board1 where seq > ? order by seq asc limit 1";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				to.setNextseq( rs.getString( "seq" ) );
				to.setNextsub( rs.getString( "subject" ) );
			}
			
			sql = "select seq, subject from img_board1 where seq < ? order by seq desc limit 1";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				to.setBeseq( rs.getString( "seq" ) );
				to.setBeseq( rs.getString( "subject" ) );
			}
			
			
			sql = "select subject, writer, mail, wip, wdate, hit, content, filename, filesize from img_board1 where seq=?";
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
			
			String sql = "select subject, writer, mail, content, filename from img_board1 where seq=?";
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
			conn = this.dataSource.getConnection();
			
			String sql = "select filename from img_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			String oldfilename = null;
			if( rs.next() ) {
				oldfilename = rs.getString( "filename" );
			}
			
			if( to.getFilename() != null ) {
				// 새로운 파일 있음
				sql = "update img_board1 set subject=?, mail=?, content=?, filename=?, filesize=? where seq=? and password=?";
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
				sql = "update img_board1 set subject=?, mail=?, content=? where seq=? and password=?";
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
			
			String sql = "select subject, writer from img_board1 where seq=?";
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

			String sql = "select filename from img_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );
			
			rs = pstmt.executeQuery();
			
			to.setFilename( null );
			if( rs.next() ) {
				to.setFilename( rs.getString( "filename" ) );
			}

			sql = "delete from img_board1 where seq=? and password=?";
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
