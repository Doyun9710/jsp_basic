package pack6;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeptTO to = new DeptTO( "10", "연구부" );
		
		System.out.println( "pack6" );
		System.out.println( to.getDeptno() );
		System.out.println( to.getDname() );
		System.out.println( to.getLoc() );
		
		/* Error
		DeptTO2 to2 = new DeptTO2( "10", "연구부" );
		
		System.out.println( "pack6" );
		System.out.println( to2.getDeptno() );
		System.out.println( to2.getDname() );
		System.out.println( to2.getLoc() );
		*/
	}

}
