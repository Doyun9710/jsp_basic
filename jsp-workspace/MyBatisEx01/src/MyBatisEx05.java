import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model1.DeptTO1;
import model1.DeptTO2;

public class MyBatisEx05 {

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
			
			DeptTO1 to1 = (DeptTO1)sqlSession.selectOne( "selecttoone" );
			System.out.println( to1.getDeptno() );
			System.out.println( to1.getDname() );
			System.out.println( to1.getLoc() );
			
			DeptTO2 to2 = (DeptTO2)sqlSession.selectOne( "selecttoone2" );
			System.out.println( to2.getDeptno() );
			System.out.println( to2.getDname() );
			System.out.println( to2.getLoc() );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( sqlSession != null ) sqlSession.close();
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}

}
