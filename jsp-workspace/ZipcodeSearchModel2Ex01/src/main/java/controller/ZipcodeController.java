package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.Action;
import model2.ZipcodeAction;
import model2.ZipcodeOkAction;

/**
 * Servlet implementation class ZipcodeController
 */
@WebServlet("/controller")
public class ZipcodeController extends HttpServlet {
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
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			request.setCharacterEncoding( "utf-8" );
			
			String path = request.getParameter( "path" );
			
			String url = "/model2/error.jsp";
			Action action = null;
			if( path == null || path.equals( "" ) || path.equals( "zipcode" ) ) {
				action = new ZipcodeAction();
				action.execute(request, response);
				
				url = "/model2/zipcode.jsp";
			} else if( path.equals( "zipcode_ok" ) ) {
				action = new ZipcodeOkAction();
				action.execute(request, response);
				
				url = "/model2/zipcode_ok.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher( url );
			dispatcher.forward(request, response);
			
		} catch (UnsupportedEncodingException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch (ServletException e) {
			System.out.println( "[에러] " + e.getMessage() );
		} catch (IOException e) {
			System.out.println( "[에러] " + e.getMessage() );
		}
	}

}
