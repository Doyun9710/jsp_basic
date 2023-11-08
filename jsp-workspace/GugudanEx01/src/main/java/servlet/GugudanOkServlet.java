package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GugudanOkServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding( "utf-8" );
			
			String startdan = req.getParameter("startdan") == null ? "" : req.getParameter("startdan");
			String enddan = req.getParameter("enddan") == null ? "" : req.getParameter("enddan");
			
			int iStartdan = Integer.parseInt( startdan );
			int iEnddan = Integer.parseInt( enddan );

			StringBuilder gugudanHtml = new StringBuilder();
			gugudanHtml.append( "<table border='1' width='800'>" );
			for( int i=iStartdan ; i<=iEnddan ; i++ ) {
				gugudanHtml.append( "<tr>" );
				for( int j=1 ; j<=9 ; j++ ) {
					gugudanHtml.append( "<td>" + i + " X " + j + " = " + ( i*j )+ "</td>" );
				}
				gugudanHtml.append( "</tr>" );
			}
			gugudanHtml.append( "</table>" );

			resp.setContentType( "text/html;charset=utf-8" );
			
			StringBuilder sbHtml = new StringBuilder();
			
			sbHtml.append( "<!doctype html>" );
			sbHtml.append( "<html>" );
			sbHtml.append( "<head>" );
			sbHtml.append( "<meta charset='utf-8' />" );
			sbHtml.append( "</head>" );
			sbHtml.append( "<body>" );
			
			sbHtml.append( gugudanHtml );

			sbHtml.append( "</body>" );
			sbHtml.append( "</html>" );
			
			PrintWriter out = resp.getWriter();
			out.println( sbHtml );
			out.close();
			
		} catch (UnsupportedEncodingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}	
	
}
