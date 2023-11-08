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
 * Servlet implementation class ControllerEx01
 */
@WebServlet("*.do")
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
		// TODO Auto-generated method stub
		
		// http://localhost:8080/XXX/view1.do
		// http://localhost:8080/XXX/view2.do
		
		// http://localhost:8080/XXX/board/view1.do
		// http://localhost:8080/XXX/board/view2.do
		
		 System.out.println( request.getRequestURI() );
		 System.out.println( request.getContextPath() );
		
		try {
			request.setCharacterEncoding( "utf-8" );
			
			// 프로젝트명 제거
			String path = request.getRequestURI().replace(request.getContextPath(), "");
			
			String url = "/model2/error.jsp";
			Action action = null;
			if( path.equals("/") || path.equals("*") || path.equals("/view1.do") ) {
				url = "/views/view1.jsp";
			} else if( path.equals("/view2.do") ) {
				url = "/views/view2.jsp";
			} else if( path.equals( "/zipcode.do" ) ) {
				action = new ZipcodeAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/zipcode.jsp";
			} else if( path.equals( "/zipcode_ok.do" ) ) {
				action = new ZipcodeOkAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/zipcode_ok.jsp";
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
