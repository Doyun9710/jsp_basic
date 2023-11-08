import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model1.ZipcodeTO;

public class MyBatisEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String resource = "myBatisConfig.xml";
		
		InputStream is = null;
		SqlSession sqlSession = null;
		
		try {
			is = Resources.getResourceAsStream( resource );
			SqlSessionFactory sqlSessionFactory
			= new SqlSessionFactoryBuilder().build( is );
			
			sqlSession = sqlSessionFactory.openSession();
			
			List<ZipcodeTO> lists = sqlSession.selectList( "selectparamlist1", "신사동" );

			for( int i=0 ; i<lists.size() ; i++ ) {
				ZipcodeTO to = lists.get(i);
				System.out.printf( "[%s] %s %s %s %s %s %n",
					to.getZipcode(), to.getSido(), to.getGugun(),
					to.getDong(), to.getRi(), to.getBunji() );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if( sqlSession != null ) sqlSession.close();
			if( is != null ) try { is.close(); } catch(IOException e) {}
		}
	}
}










