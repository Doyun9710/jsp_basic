package pack4;

import pack4.BoardTO;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardTO to1 = new BoardTO();
		BoardTO to2 = new BoardTO("제목", "작성자");
		
		System.out.println( "pack4" );
		System.out.println( to2.getSubject() );
		System.out.println( to2.getWriter() );
	}

}
