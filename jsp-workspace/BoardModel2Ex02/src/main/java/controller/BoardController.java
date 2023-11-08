package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardAction;
import model2.DeleteAction;
import model2.DeleteOkAction;
import model2.ListAction;
import model2.ModifyAction;
import model2.ModifyOkAction;
import model2.ViewAction;
import model2.WriteAction;
import model2.WriteOkAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.do")
public class BoardController extends HttpServlet {
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
			
			// 프로젝트명 제거
			String path = request.getRequestURI().replace(request.getContextPath(), "");
			
			String url = "/WEB-INF/model2/error.jsp";
			BoardAction action = null;
			if( path == null || path.equals( "" ) || path.equals( "/list.do" ) ) {
				action = new ListAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_list1.jsp";
			} else if( path.equals( "/view.do" ) ) {
				action = new ViewAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_view1.jsp";
			} else if( path.equals( "/write.do" ) ) {
				action = new WriteAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_write1.jsp";
			} else if( path.equals( "/write_ok.do" ) ) {
				action = new WriteOkAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_write1_ok.jsp";
			} else if( path.equals( "/modify.do" ) ) {
				action = new ModifyAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_modify1.jsp";
			} else if( path.equals( "/modify_ok.do" ) ) {
				action = new ModifyOkAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_modify1_ok.jsp";
			} else if( path.equals( "/delete.do" ) ) {
				action = new DeleteAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_delete1.jsp";
			} else if( path.equals( "/delete_ok.do" ) ) {
				action = new DeleteOkAction();
				action.execute(request, response);
				
				url = "/WEB-INF/model2/board_delete1_ok.jsp";
			}
			System.out.println( path );
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
