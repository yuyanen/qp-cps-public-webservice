package qp.scs.dto.request;

import org.hibernate.validator.constraints.NotBlank;
public class LoginRequestDTO {

	@NotBlank(message ="username can't be blank")
	public String username;
	
	@NotBlank(message ="username can't be blank")
	public String password;
	
}
