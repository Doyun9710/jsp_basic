package servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JDBCServletEx01 extends HttpServlet {
	
	private Connection conn;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println( "doGet() 호출" );
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println( "doPost() 호출" );
		this.doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) {
		
		//Connection conn = null;
		
		// 출력
		try {
			req.setCharacterEncoding( "utf-8" );
			
			resp.setContentType( "text/html;charset=utf-8" );
			
			// DB 연동
			String url = "jdbc:mariadb://localhost:3306/project";
			String user = "root";
			String password = "!123456";
			
			Class.forName( "org.mariadb.jdbc.Driver" );
			System.out.println( "드라이버 로딩 성공" );
			
			this.conn = DriverManager.getConnection(url, user, password);
			System.out.println( "연결성공");
			
			PrintWriter out = resp.getWriter();
			out.println();
			out.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println( "에러 " + e.getMessage() );
		} catch (ClassNotFoundException e) {
			System.out.println( "에러 " + e.getMessage() );
		} catch (SQLException e) {
			System.out.println( "에러 " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "에러 " + e.getMessage() );
		} finally {
			//if( rs != null ) try { rs.close(); } catch(SQLException e) {}
			//if( pstmt != null ) try { pstmt.close(); } catch(SQLException e) {}
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
	}

}
