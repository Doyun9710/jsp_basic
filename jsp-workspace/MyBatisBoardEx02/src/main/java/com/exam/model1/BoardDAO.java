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

import com.exam.mapper.BoardMapperInter;

public class BoardDAO {
	private SqlSession sqlSession;
	private BoardMapperInter mapper;
	
	private DataSource dataSource = null;
	private String uploadPath = "C:/Java/jsp-workspace/MyBatisBoardEx02/src/main/webapp/upload";
	
	public BoardDAO() {
		String resource = "myBatisConfig.xml";

		InputStream is = null;
		
		try {
			is = Resources.getResourceAsStream( resource );
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build( is );
			
			this.sqlSession = sqlSessionFactory.openSession( true );
			
			this.sqlSession.getConfiguration().addMapper( BoardMapperInter.class );
			this.mapper = (BoardMapperInter)sqlSession.getMapper( BoardMapperInter.class );
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
	public BoardListTO boardList(BoardListTO listTO) {
		int cpage = listTO.getCpage();
		int recordPerPage = listTO.getRecordPerPage();
		int blockPerPage = listTO.getBlockPerPage();

		//List<BoardTO> lists = this.mapper.list();
		List<BoardTO> lists = null;
		
		if( listTO.getSearchWord().equals( "" ) ) {
			lists = mapper.list();
		} else {
			lists = mapper.listSearch( listTO );
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
		int result = this.mapper.write_ok( to );
		if( result == 1 ) {
			flag = 0;
		}
		
		if( sqlSession != null ) sqlSession.close();
		return flag;
	}

	public BoardTO boardView(BoardTO to) {
		this.mapper.view_hit( to );
		
		to = this.mapper.view( to );
		// if( to.getContent() == null ) to.setContent( "없음" );
		to.setFilesize( to.getFilesize() / 1024 );
		
		BoardTO toN = this.mapper.view_nextpage( to );
		if( toN != null ) {
			to.setNextseq( toN.getSeq() );
			to.setNextsub( toN.getSubject() );
		}
		
		BoardTO toL = this.mapper.view_lastpage( to );
		if( toL != null ) {
			to.setBeseq( toL.getSeq() );
			to.setBesub( toL.getSubject() );
		}

		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public BoardTO boardModify(BoardTO to) {
		to = this.mapper.modify( to );

		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public int boardModifyOk(BoardTO to) {
		String oldfilename = this.mapper.modify_ok_select_filename( to );
		if( to.getFilename() == null) to.setFilename(oldfilename);

		int flag = 2;
		int result = this.mapper.modify_ok( to );

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
			if( oldfilename != to.getFilename() ) {
				File file = new File( uploadPath, oldfilename );
				file.delete();
			}
		}

		if( sqlSession != null ) sqlSession.close();
		return flag;
	}
	
	
	public BoardTO boardDelete(BoardTO to) {
		to = this.mapper.delete( to );

		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public int boardDeleteOk(BoardTO to) {
		String deleteFileName = this.mapper.delete_select_filename( to );
		
		int flag = 2;
		int result = this.mapper.delete_ok( to );
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
