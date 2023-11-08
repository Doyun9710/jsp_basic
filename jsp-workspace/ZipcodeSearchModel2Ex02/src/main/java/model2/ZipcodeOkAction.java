package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ZipcodeDAO;
import model1.ZipcodeTO;

public class ZipcodeOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println( "ZipcodeOkAction 호출" );
		
		String strdong = request.getParameter( "strdong" );
		
		ZipcodeDAO dao = new ZipcodeDAO();
		ArrayList<ZipcodeTO> datas = dao.searchZipcode(strdong);

		request.setAttribute( "datas", datas );
	}
}
