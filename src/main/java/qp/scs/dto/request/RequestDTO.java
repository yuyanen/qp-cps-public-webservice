package qp.scs.dto.request;

import org.hibernate.validator.constraints.NotBlank;
public class RequestDTO {

	@NotBlank(message ="token can't be blank")
	public String token;
	
}
