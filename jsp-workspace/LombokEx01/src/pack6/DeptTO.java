package pack6;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeptTO {
	@NonNull private String deptno;
	@NonNull private String dname;
	private String loc;
}
