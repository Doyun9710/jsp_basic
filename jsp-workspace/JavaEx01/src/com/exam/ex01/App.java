package com.exam.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) {
		System.out.println( "test" );
		
		String url = "jdbc:mariadb://localhost:3306/project";
		String user = "root";
		String password = "!123456";
		
		Connection conn = null;
		
		try {
			Class.forName( "org.mariadb.jdbc.Driver" );
			System.out.println( "드라이버 로딩 성공" );

			conn = DriverManager.getConnection(url, user, password);
			System.out.println( "연결 성공" );
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} finally {
			if( conn != null ) try { conn.close(); } catch(SQLException e) {}
		}
	}
	
}
