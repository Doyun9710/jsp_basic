package com.exam.model1;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exam.mapper.BoardMapperInter;

public class BoardDAO {
	private SqlSession sqlSession;
	private BoardMapperInter mapper;
	
	public BoardDAO() {
		// TODO Auto-generated constructor stub
		String resource = "myBatisConfig.xml";
		
		InputStream is = null;
		
		try {
			is = Resources.getResourceAsStream( resource );
			SqlSessionFactory sqlSessionFactory
			= new SqlSessionFactoryBuilder().build( is );
			
			this.sqlSession = sqlSessionFactory.openSession( true );
			
			this.sqlSession.getConfiguration().addMapper( 
					BoardMapperInter.class );
			this.mapper = (BoardMapperInter)sqlSession.getMapper( 
					BoardMapperInter.class );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( is != null ) try { is.close(); } catch( IOException e ) {}
		}
	}

	public BoardListTO boardList(BoardListTO listTO) {
		List<BoardTO> lists = null;
		
		if( listTO.getSearchWord().equals( "" ) ) {
			lists = mapper.boardList();
		} else {
			lists = mapper.boardListSearch( listTO );
		}
		
		ArrayList<BoardTO> boardLists = new ArrayList<BoardTO>();
		for( BoardTO to : lists ) {
				boardLists.add( to );
		}
		
		listTO.setBoardLists( boardLists );
		
		if( sqlSession != null ) sqlSession.close();
		return listTO;
	}
	
	public void boardWrite() {}
	
	public int boardWriteOk(BoardTO to) {
		int flag = 1;
		
		int result = mapper.boardWriteOK( to );
		if( result == 1 ) {
			flag = 0;
		}
		
		if( sqlSession != null ) sqlSession.close();
		return flag;
	}
	
	public BoardTO boardView(BoardTO to) {
		mapper.boardViewHit(to);
		
		to = mapper.boardView( to );
		
		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public BoardTO boardModify(BoardTO to) {
		to = mapper.boardModify( to );
		
		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public int boardModifyOk(BoardTO to) {
		int flag = 1;
		int result = mapper.boardModifyOk( to );
		if( result == 1 ) {
			flag = 0;
		}
		
		if( sqlSession != null ) sqlSession.close();
		return flag;
	}

	
	public BoardTO boardDelete(BoardTO to) {
		to = mapper.boardDelete( to );
		
		if( sqlSession != null ) sqlSession.close();
		return to;
	}
	
	public int boardDeleteOk(BoardTO to) {
		int flag = 1;
		
		int result = mapper.boardDeleteOk( to );
		if( result == 1 ) {
			flag = 0;
		}
		
		if( sqlSession != null ) sqlSession.close();
		return flag;
	}
}