package com.exam.model1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAO {
	private SqlSession sqlSession;
	
	private DataSource dataSource = null;
	private String uploadPath = "C:/Java/jsp-workspace/MyBatisBoardEx01/src/main/webapp/upload";
	
	public BoardDAO() {
		String resource = "myBatisConfig.xml";

		InputStream is = null;
		
		try {
			is = Resources.getResourceAsStream( resource );
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build( is );
			
			this.sqlSession = sqlSessionFactory.openSession( true );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}
	
	/*
	public List<BoardTO> boardList() {
		List<BoardTO> lists = sqlSession.selectList( "list" );
		
		if( sqlSession != null ) sqlSession.close();
		return lists;
	}
	*/
	/*
	public BoardListTO boardList(BoardListTO listTO) {
		// paging
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();

		List<BoardTO> lists = sqlSession.selectList( "list" );
		
		listTO.setTotalRecord( lists.size() );
		
		listTO.setTotalPage( ( ( listTO.getTotalRecord() - 1 ) / recordPerPage ) + 1);
		
		
		// Page Skip
		int count = 1;
		int skip = ( cpage - 1 ) * recordPerPage;
		//if( skip != 0 ) rs.absolute( skip );
		

		ArrayList<BoardTO> boardLists = new ArrayList<BoardTO>();
		for( BoardTO to : lists ) {
			if( count > skip && count <= skip+10 )
				boardLists.add( to );
			count++;
		}
		
		listTO.setBoardLists( boardLists );
		
		listTO.setStartBlock( cpage - ( cpage-1 ) % blockPerPage );
		listTO.setEndBlock( cpage - ( cpage-1 ) % blockPerPage + blockPerPage - 1 );
		
		if( listTO.getEndBlock() >= listTO.getTotalPage() ) {
			listTO.setEndBlock( listTO.getTotalPage() );
		}

		if( sqlSession != null ) sqlSession.close();
		return listTO;
	}
	*/
	public BoardListTO boardList(BoardListTO listTO) {
		// paging
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();

		//List<BoardTO> lists = sqlSession.selectList( "list" );
		List<BoardTO> lists = null;
		
		if( listTO.getSearchWord().equals( "" ) ) {
			lists = sqlSession.selectList( "list" );
		} else {
			lists = sqlSession.selectList( "list_search", listTO );
		}
		
		listTO.setTotalRecord( lists.size() );
		
		listTO.setTotalPage( ( ( listTO.getTotalRecord() - 1 ) / recordPerPage ) + 1);
		
		
		// Page Skip
		int count = 1;
		int skip = ( cpage - 1 ) * recordPerPage;
		//if( skip != 0 ) rs.absolute( skip );
		

		ArrayList<BoardTO> boardLists = new ArrayList<BoardTO>();
		for( BoardTO to : lists ) {
			if( count > skip && count <= skip+10 )
				boardLists.add( to );
			count++;
		}
		
		listTO.setBoardLists( boardLists );
		
		listTO.setStartBlock( cpage - ( cpage-1 ) % blockPerPage );
		listTO.setEndBlock( cpage - ( cpage-1 ) % blockPerPage + blockPerPage - 1 );
		
		if( listTO.getEndBlock() >= listTO.getTotalPage() ) {
			listTO.setEndBlock( listTO.getTotalPage() );
		}

		if( sqlSession != null ) sqlSession.close();
		return listTO;
	}


	public void boardWrite() {}
	
	public int boardWriteOk(BoardTO to) {
		int flag = 1;
		int result = sqlSession.insert( "write_ok", to );
		if( result == 1 ) {
			flag = 0;
		}
		
		if( sqlSession != null ) sqlSession.close();
		return flag;
	}

	public BoardTO boardView(BoardTO to) {
		sqlSession.update( "view_hit", to );
		
		to = sqlSession.selectOne( "view", to );
		// if( to.getContent() == null ) to.setContent( "없음" );
		to.setFilesize( to.getFilesize() / 1024 );
		
		BoardTO toN = sqlSession.selectOne( "view_nextpage", to );
		if( toN != null ) {
			to.setNextseq( toN.getSeq() );
			to.setNextsub( toN.getSubject() );
		}
		
		BoardTO toL = sqlSession.selectOne( "view_lastpage", to );
		if( toL != null ) {
			to.setBeseq( toL.getSeq() );
			to.setBesub( toL.getSubject() );
		}

		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public BoardTO boardModify(BoardTO to) {
		to = sqlSession.selectOne( "modify", to );

		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public int boardModifyOk(BoardTO to) {
		String oldfilename = sqlSession.selectOne( "modify_ok_select_filename", to );

		int flag = 2;
		int result = sqlSession.update( "modify_ok", to );

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

		if( sqlSession != null ) sqlSession.close();
		return flag;
	}
	
	
	public BoardTO boardDelete(BoardTO to) {
		to = sqlSession.selectOne( "delete", to );

		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public int boardDeleteOk(BoardTO to) {
		String deleteFileName = sqlSession.selectOne( "delete_select_filename", to );
		
		int flag = 2;
		int result = sqlSession.update( "delete_ok", to );
		if( result == 1 ) {
			flag = 0;
			
			if( deleteFileName != null ) {
				File file = new File( uploadPath, deleteFileName );
				file.delete();
			}
		} else if( result == 0 ) {
			flag = 1;
		}
		
		/*
		try {
			conn = dataSource.getConnection();

			String sql = "select filename from img_board1 where seq=?";
			pstmt = conn.prepareStatement( sql );
			pstmt.setString( 1, to.getSeq() );

			if( result == 0 ) {
				flag = 1;	// 비밀번호 오류
			} else if( result == 1 ) {
				flag = 0;	// 정상
				// 실제 파일 삭제
				if( to.getFilename() != null ) {
					File file = new File( uploadPath, to.getFilename() );
					file.delete();
				}
			}

		*/
		if( sqlSession != null ) sqlSession.close();
		return flag;
	}
}
