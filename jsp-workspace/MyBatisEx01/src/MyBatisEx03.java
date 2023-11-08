import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
			
			sqlSession = sqlSessionFactory.openSession();
			
			System.out.println( "연결 성공" );
			
			// selectOne : 반드시 한 행만을 리턴
			Map map = sqlSession.selectOne( "selectone" );
			// 컬럼의 개수
			System.out.println( map.size() );
			
			/*
			// 방법 1
			Set<String> keys = map.keySet();
			for( String key : keys ) {
				System.out.println( key + "/" + map.get(key) );
			}
			*/
			
			// 방법 2
			System.out.println( map.get( "deptno" ) );
			System.out.println( map.get( "dname" ) );
			System.out.println( map.get( "loc" ) );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( sqlSession != null ) sqlSession.close();
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}

}
