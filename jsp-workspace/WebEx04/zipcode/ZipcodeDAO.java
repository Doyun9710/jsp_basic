import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZipcodeDAO {
	// DAO : 데이터베이스 연결 클래스 ( Data Access Object )
	
	private Connection conn;
	
	// 데이터베이스 연결
	public ZipcodeDAO() {
		
		String url = "jdbc:mariadb://localhost:3306/project";
		String user = "project";
		String password = "123456";
		
		try {
			Class.forName( "org.mariadb.jdbc.Driver" );
			this.conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println( "에러 " + e.getMessage() );
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} 
		// finally 선언 시 종료됨
		
	}
	
//	// 각 쿼리별 메서드 생성
////	public ArrayList<String> searchZipcode( String strDong ) {
//	public ArrayList<ZipcodeTO> searchZipcode( String strDong ) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
////		ArrayList<String> datas = new ArrayList<String>();
//		ArrayList<ZipcodeTO> datas = new ArrayList<ZipcodeTO>();
//		
//		try {
//			String sql = "select zipcode, sido, gugun, dong, ri, bunji from zipcode where dong like ?";
//			pstmt = this.conn.prepareStatement( sql );
//			pstmt.setString( 1, strDong + "%" );
//			
//			rs = pstmt.executeQuery();
//			
//			while( rs.next() ) {
//
//				ZipcodeTO to = new ZipcodeTO();
//				
//				to.setZipcode( rs.getString( "zipcode" ) );
//				to.setSido( rs.getString( "sido" ) );
//				to.setGugun( rs.getString( "gugun" ) );
//				to.setDong( rs.getString( "dong" ) );
//				to.setRi( rs.getString( "ri" ) );
//				to.setBunji( rs.getString( "bunji" ) );
//				
//				datas.add( to );
//			}
//		} catch (SQLException e) {
//			System.out.println( "에러 " + e.getMessage() );
//		} finally {
//			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
//			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
//			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
//		}
//		
//		return datas;
//	}
	
	public ArrayList<String> listSido() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> datas = new ArrayList<String>();
		
		try {
			String sql = "select distinct sido from zipcode";
			pstmt = this.conn.prepareStatement( sql );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				datas.add( rs.getString("sido") );
			}
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
	
	public ArrayList<String> listGugun(String sido) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> datas = new ArrayList<String>();
		
		try {
			String sql = "select distinct gugun from zipcode where sido like ?";
			pstmt = this.conn.prepareStatement( sql );
			pstmt.setString( 1, sido );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				datas.add( rs.getString("gugun") );
			}
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
	
	public ArrayList<String> listDong(String sido, String gugun) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> datas = new ArrayList<String>();
		
		try {
			String sql = "select distinct dong from zipcode where sido = ? && gugun like ?";
			pstmt = this.conn.prepareStatement( sql );
			pstmt.setString( 1, sido );
			pstmt.setString( 2, gugun );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				datas.add( rs.getString("dong") );
			}
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
	
	public ArrayList<String> searchSido(String sido) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> datas = new ArrayList<String>();
		
		try {
			String sql = "select zipcode, sido, gugun, dong, ri, bunji from zipcode where sido like ?";
			pstmt = this.conn.prepareStatement( sql );
			pstmt.setString( 1, sido );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				datas.add( rs.getString("zipcode") + "\t" + rs.getString("sido") + "\t" + rs.getString("gugun") 
				+ "\t" + rs.getString("dong") + "\t" + rs.getString("ri") + "\t" + rs.getString("bunji") );
			}
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
	
	public ArrayList<String> searchGugun(String sido, String gugun) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> datas = new ArrayList<String>();
		
		try {
			String sql = "select zipcode, sido, gugun, dong, ri, bunji from zipcode where sido = ? && gugun like ?";
			pstmt = this.conn.prepareStatement( sql );
			pstmt.setString( 1, sido );
			pstmt.setString( 2, gugun );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				datas.add( rs.getString("zipcode") + "\t" + rs.getString("sido") + "\t" + rs.getString("gugun") 
				+ "\t" + rs.getString("dong") + "\t" + rs.getString("ri") + "\t" + rs.getString("bunji") );
			}
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
	
	public ArrayList<String> searchDong(String sido, String gugun, String dong) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<String> datas = new ArrayList<String>();
		
		try {
			String sql = "select zipcode, sido, gugun, dong, ri, bunji from zipcode where sido = ? && gugun = ? && dong like ?";
			pstmt = this.conn.prepareStatement( sql );
			pstmt.setString( 1, sido );
			pstmt.setString( 2, gugun );
			pstmt.setString( 3, dong );
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				datas.add( rs.getString("zipcode") + "\t" + rs.getString("sido") + "\t" + rs.getString("gugun") 
				+ "\t" + rs.getString("dong") + "\t" + rs.getString("ri") + "\t" + rs.getString("bunji") );
			}
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
		
		return datas;
	}
}
