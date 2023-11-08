import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model1.ZipcodeTO;

public class MyBatisEx03 {

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
			
			ZipcodeTO paramTO = new ZipcodeTO();
			paramTO.setGugun( "강남구" );
			paramTO.setDong( "대치1동" );
			
			List<ZipcodeTO> lists = sqlSession.selectList( "selectparamlist2", paramTO );

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










