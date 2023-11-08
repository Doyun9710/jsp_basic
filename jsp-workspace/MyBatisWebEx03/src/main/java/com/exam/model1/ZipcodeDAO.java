package com.exam.model1;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ZipcodeDAO {
	private SqlSession sqlSession;
	
	public ZipcodeDAO() {
		String resource = "myBatisConfig.xml";

		InputStream is = null;
		
		try {
			is = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build( is );
			
			this.sqlSession = sqlSessionFactory.openSession( true );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}
	
	public List<ZipcodeTO> selectlist( String strdong ) {
		List<ZipcodeTO> lists = sqlSession.selectList( "selectlist", strdong );
		if( sqlSession != null ) sqlSession.close();
		return lists;
	}
}
