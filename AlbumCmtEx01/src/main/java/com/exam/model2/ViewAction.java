package com.exam.model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model1.BoardDAO;
import com.exam.model1.BoardListTO;
import com.exam.model1.BoardReplyTO;
import com.exam.model1.BoardTO;

public class ViewAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "ViewAction 호출" );
		
		BoardTO to = new BoardTO();
		to.setSeq( request.getParameter( "seq" ) );
		
		BoardDAO dao = new BoardDAO();
		to = dao.boardView( to );
		
		request.setAttribute( "to", to );
		
		BoardReplyTO listRTO = new BoardReplyTO();
		listRTO.setSeq( request.getParameter( "seq" ) );
		listRTO = dao.boardReplyList( listRTO );
		
		ArrayList<BoardReplyTO> boardReplyLists = listRTO.getBoardReplyLists();
		
		request.setAttribute( "boardReplyLists", boardReplyLists);
	}

}
