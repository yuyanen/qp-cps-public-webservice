package qp.cps.dto.response;

public class ProfileDetailResponseDTO {
	public String username;
	
	//public boolean isFirstTimeLogin;

	public ProfileDetailResponseDTO() {
	}

	public ProfileDetailResponseDTO(String username) {
		this.username=username;
		//this.isFirstTimeLogin = isFirstTimeLogin;
	}
}
