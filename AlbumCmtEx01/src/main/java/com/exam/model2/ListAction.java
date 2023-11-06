package com.exam.model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model1.BoardDAO;
import com.exam.model1.BoardListTO;
import com.exam.model1.BoardTO;

public class ListAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "ListAction 호출" );
		
		int cpage = 1;
		if( request.getParameter( "cpage" ) != null
				&& !request.getParameter( "cpage" ).equals( "" ) ) {
			cpage = Integer.parseInt( request.getParameter( "cpage" ) );
		}

		BoardListTO listTO = new BoardListTO();
		listTO.setCpage( cpage );
		
		BoardDAO dao = new BoardDAO();
		listTO = dao.boardList( listTO );
		
		request.setAttribute( "listTO", listTO );
		
		ArrayList<BoardTO> boardLists = listTO.getBoardLists();
		
		request.setAttribute( "boardLists", boardLists);
	}

}
