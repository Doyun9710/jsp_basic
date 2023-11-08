package pack5;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoardTO to = new BoardTO(null, null);
		
		// NullPointerException
		System.out.println( "pack5" );
		System.out.println( to.getSubject() );
		System.out.println( to.getWriter() );
	}

}
