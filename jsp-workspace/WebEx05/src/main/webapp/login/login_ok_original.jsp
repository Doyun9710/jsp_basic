<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- login_ok.jsp -->
<%
	request.setCharacterEncoding( "utf-8" );

	String id = request.getParameter( "id" );
	String password = request.getParameter( "password" );

	// 저장된 아이디, 패스워드
	String save_id = "tester";
	String save_password = "1234";
	

	// flag
	// 0 - 로그인
	// 1 - 아이디/비밀번호 오류
	// 2 - 기타 오류
	int flag = 2;
	if( save_id.equals( id ) && save_password.equals( password ) ) {
		// 성공
		flag = 0;
		
		// 세션 부여, 개인정보(X)
		session.setAttribute( "sid", id );
		session.setAttribute( "sgrade", "B" );
	} else {
		// 실패
		flag = 1;
	}

	// 리다이렉션
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert('로그인 되었습니다')" );
		out.println( "location.href='login_complete.jsp';" );
	} else if( flag == 1 ) {
		out.println( "alert('아이디 비밀번호 오류');" );
		out.println( "history.back();" );
	} else {
		out.println( "alert('기타 오류');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>