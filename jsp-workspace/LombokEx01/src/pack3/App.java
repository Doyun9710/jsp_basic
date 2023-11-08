package pack3;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardTO to = new BoardTO();
		to.setSubject( "제목" );
		to.setWriter( "작성자" );
		
		System.out.println( "pack3" );
		System.out.println( to.getSubject() );
		System.out.println( to.getWriter() );
	}

}
