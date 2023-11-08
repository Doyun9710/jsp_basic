import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model1.DeptTO2;

public class MyBatisEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String resource = "myBatisConfig.xml";
		
		InputStream is = null;
		SqlSession sqlSession = null;
		
		try {
			is = Resources.getResourceAsStream( resource );
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build( is );
			System.out.println( "설정 호출 성공" );
			
			sqlSession = sqlSessionFactory.openSession( true );
			
			//DeptTO2 to = sqlSession.selectOne( "selectone1" );
			//DeptTO2 to = sqlSession.selectOne( "selectone2" );
			DeptTO2 to = sqlSession.selectOne( "mybatis1.selectone2" );
			
			System.out.println( to.getDeptno() );
			System.out.println( to.getDname() );
			System.out.println( to.getLoc() );
			
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( sqlSession != null ) sqlSession.close();
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}

}
