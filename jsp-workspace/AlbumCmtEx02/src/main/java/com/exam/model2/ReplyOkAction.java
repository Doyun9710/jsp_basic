package com.exam.model2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exam.model1.BoardDAO;
import com.exam.model1.BoardReplyTO;
import com.exam.model1.BoardTO;

public class ReplyOkAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "ReplyOkAction 호출" );
		
		BoardReplyTO rto = new BoardReplyTO();
		rto.setSeq( request.getParameter( "pseq" ) );
		rto.setCwriter( request.getParameter( "cwriter" ) );
		rto.setCpassword( request.getParameter( "cpassword" ) );
		rto.setCcontent( request.getParameter( "ccontent" ) );

		BoardDAO dao = new BoardDAO();
		int flag = dao.boardReplyOk( rto );
		
		request.setAttribute( "flag", flag );
	}
}
