package pack7;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeptTO to = new DeptTO();
		
		to.setDeptno( "10" );
		to.setDname( "연구부" );
		to.setLoc( "부산" );
		
		System.out.println( to.toString() );
	}

}
