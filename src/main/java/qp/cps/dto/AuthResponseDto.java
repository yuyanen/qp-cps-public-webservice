package qp.cps.dto;

public class AuthResponseDto {

	private final boolean auth;

	private final String status;

	private final String reason;
	
	private final AuthUserDto user;


	public AuthResponseDto(boolean auth, String status, String reason, AuthUserDto user) {
		this.auth = auth;
		this.status = status;
		this.reason = reason;
		this.user = user;
	}

	public boolean isAuth() {
		return auth;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}
	
	public AuthUserDto getUser() {
		return user;
	}
}
