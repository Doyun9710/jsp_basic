package servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx05 extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println( "init() 호출 5" );
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Get 방식처리
		System.out.println( "doGet() 호출" );
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Post 방식처리
		System.out.println( "doPost() 호출" );
		this.doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			// 통합처리
			req.setCharacterEncoding( "utf-8" );
			
			String data = req.getParameter( "data" );
			
			resp.setContentType( "text/html;charset=utf-8" );
			
			StringBuilder sbHtml = new StringBuilder();
			sbHtml.append( "<!doctype html>" );
			sbHtml.append( "<html>" );
			sbHtml.append( "<head>" );
			sbHtml.append( "<meta charset='utf-8' />" );
			sbHtml.append( "</head>" );
			sbHtml.append( "<body>" );
			sbHtml.append( "Hello Servlet ");
			sbHtml.append( "</body>" );
			sbHtml.append( "</html>" );
			
			PrintWriter out = resp.getWriter();
			out.println( sbHtml.toString() );
			out.close();
		} catch (UnsupportedEncodingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}
}
