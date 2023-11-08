package model1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View1Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "View1Action 호출" );
	}
}
