import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisEx02 {

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
			
			int result = sqlSession.update( "dropTable1" );
			
			String sql = "create table testble2 (col1 varchar(10) )";
			//int result = sqlSession.update( "createTable2", sql );
			System.out.println( "결과 : " + result );
			
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( sqlSession != null ) sqlSession.close();
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}

}
