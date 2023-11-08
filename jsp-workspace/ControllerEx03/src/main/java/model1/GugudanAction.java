package model1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GugudanAction {
	
	public void execute(HttpServletRequest request
				, HttpServletResponse response) {
		System.out.println( "GugudanAction 호출" );
	}
}
