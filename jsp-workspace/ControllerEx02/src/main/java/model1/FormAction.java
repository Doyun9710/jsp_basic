package model1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormAction {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "FormAction 호출" );
	}
	
}
