package pack5;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardTO {
	@NonNull private String subject;
	@NonNull private String writer;
}
