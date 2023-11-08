package servlet1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx01 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println( "서블릿 호출" );
		
		resp.setContentType( "text/html;charset=utf-8" );
		
		StringBuilder sbHtml = new StringBuilder();
		sbHtml.append( "<!doctype html>" );
		sbHtml.append( "<html>" );
		sbHtml.append( "<head>" );
		sbHtml.append( "<meta charset='utf-8' />" );
		sbHtml.append( "</head>" );
		sbHtml.append( "<body>" );
		sbHtml.append( "Hello Servlet" );
		sbHtml.append( "</body>" );
		sbHtml.append( "</html>" );
		
		PrintWriter out = resp.getWriter();
		out.println( sbHtml.toString() );
		
		/*
		out.println( "<!doctype html>" );
		out.println( "<html>" );
		out.println( "<head>" );
		out.println( "<meta charset='utf-8' />" );
		out.println( "</head>" );
		out.println( "<body>" );
		out.println( "Hello Servlet" );
		out.println( "</body>" );
		out.println( "</html>" );
		*/
	}

}
