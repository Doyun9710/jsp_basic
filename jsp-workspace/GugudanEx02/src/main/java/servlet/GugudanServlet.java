package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GugudanServlet
 */
@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.setCharacterEncoding( "utf-8" );
			
			resp.setContentType( "text/html;charset=utf-8" );
			
			StringBuilder sbHtml = new StringBuilder();
			
			sbHtml.append( "<!doctype html>" );
			sbHtml.append( "<html>" );
			sbHtml.append( "<head>" );
			sbHtml.append( "<meta charset='utf-8' />" );
			sbHtml.append( "</head>" );
			sbHtml.append( "<body>" );
			
			sbHtml.append( "<form action='./gugudan_ok' method='post'>" );
			sbHtml.append( "시작단 : <input type='text' name='startdan' />" );
			sbHtml.append( " - " );
			sbHtml.append( "끝단 : <input type='text' name='enddan' />" );
			sbHtml.append( "&nbsp;&nbsp;<input type='submit' value='구구단 출력' />" );
			sbHtml.append( "</form>" );

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
