import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisEx04 {

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
			
			List<Map<String, String>> lists = sqlSession.selectList( "selectlist" );
			System.out.println( lists.size() );
			
			for( int i=0 ; i<lists.size() ; i++ ) {
				//Map map = lists.get( i );
				map = lists.get( i );
				System.out.printf( "%s\t%s\t%s\n", map.get( "deptno" ), map.get( "dname" ), map.get( "loc" ) );
			}
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( sqlSession != null ) sqlSession.close();
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}

}
