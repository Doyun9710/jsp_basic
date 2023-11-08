package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.FormAction;
import model1.FormOkAction;

/**
 * Servlet implementation class ControllerEx01
 */
@WebServlet("/controller")
public class ControllerEx01 extends HttpServlet {
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
		// controller				(Form1Action)	form.jsp
		// controller?path=form		(Form.java)		form.jsp
		// controller?path=form_ok	(FormOk.java)	form_ok.jsp
		
		try {
			request.setCharacterEncoding( "utf-8" );
			
			String path = request.getParameter( "path" );
			
			String url = "/error.jsp";
			if( path == null || path.equals( "" ) || path.equals( "form" ) ) {
				FormAction action = new FormAction();
				action.execute(request, response);
				
				url = "/form.jsp";
			} else if( path.equals( "form_ok" ) ) {
				FormOkAction action = new FormOkAction();
				action.execute(request, response);
				
				url = "/form_ok.jsp";
			}
			
			// forward
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
