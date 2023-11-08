package model1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormOkAction {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "FormOkAction 호출" );
		
		String data = request.getParameter( "data" );
		System.out.println( data );
		
		// 비지니스 모델 => 프로그램 처리
		
		// html 문서 X
		request.setAttribute( "data", data );
	}
	
}