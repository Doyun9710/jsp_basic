package pack1;

public class MemberTO {
	private String id;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		System.out.println( "setId() 호출" );
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println( "setPassword() 호출" );
		this.password = password;
	}

}
