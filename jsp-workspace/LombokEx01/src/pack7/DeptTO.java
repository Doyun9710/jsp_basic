package pack7;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString( exclude = { "deptno" } )
@EqualsAndHashCode
public class DeptTO {
	private String deptno;
	private String dname;
	private String loc;
}
