package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( urlPatterns = { "/servlet01", "/servlet02" }, loadOnStartup = 1 )
public class ServletEx01 extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println( "ServletEx01 init()" );
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding( "utf-8" );
			
			resp.setContentType( "text/html;charset=utf-8" );
			
			PrintWriter out = resp.getWriter();
			out.println( "Hello Servlet" );
			out.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println( "에러 " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "에러 " + e.getMessage() );
		}
	}
}
