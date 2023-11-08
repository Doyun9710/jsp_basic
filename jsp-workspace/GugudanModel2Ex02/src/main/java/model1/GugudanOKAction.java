package model1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GugudanOKAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println( "GugudanOkAction 호출" );
		
		String startDan = request.getParameter( "startdan" );
		String endDan = request.getParameter( "enddan" );
		
		int iStartDan = Integer.parseInt( startDan );
		int iEndDan = Integer.parseInt( endDan );
		
		StringBuilder sbHtml = new StringBuilder();
		sbHtml.append( "<table border='1' width='800'>" );
		for( int i=iStartDan ; i<=iEndDan ; i++ ) {
			sbHtml.append( "<tr>" );
			for( int j=1 ; j<=9 ; j++) {
				sbHtml.append( "<td>" + i + "X" + j + "=" + (i*j) + "</td>" );
			}
			sbHtml.append( "</tr>" );
		}
		sbHtml.append( "</table>" );
		
		request.setAttribute( "result", sbHtml.toString() );
	}
	


}
