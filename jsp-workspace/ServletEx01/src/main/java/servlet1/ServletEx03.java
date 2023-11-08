package servlet1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx03 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println( "Get 요청" );
		
		String data = req.getParameter( "data" );
		System.out.println( "data : " + data );
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println( "Post 요청" );
		
		req.setCharacterEncoding( "utf-8" );
		
		String data = req.getParameter( "data" );
		System.out.println( "data : " + data );
	}
}
