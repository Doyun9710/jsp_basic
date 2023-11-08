package pack6;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of" )
public class DeptTO2 {
	@NonNull private String deptno;
	@NonNull private String dname;
	private String loc;
}
