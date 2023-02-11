package qp.scs.dto.response;

public class LoginResponseDTO {
	public String token;
	
	//public boolean isFirstTimeLogin;

	public LoginResponseDTO() {
	}

	public LoginResponseDTO(String token, boolean isFirstTimeLogin) {
		this.token = token;
		//this.isFirstTimeLogin = isFirstTimeLogin;
	}
}
